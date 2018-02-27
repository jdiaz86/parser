package com.ef.helper;

import com.ef.exception.ParameterNotFoundException;
import com.ef.exception.ParserException;
import com.ef.model.Log;
import com.ef.model.Param;
import com.ef.model.Result;
import static com.ef.util.Const.ACCESS_LOG_PARAM;
import static com.ef.util.Const.DELIMITER;
import static com.ef.util.Const.DURATION_PARAM;
import static com.ef.util.Const.EMPTY;
import static com.ef.util.Const.SDF_LOG;
import static com.ef.util.Const.SDF_PARAM;
import static com.ef.util.Const.START_DATE_PARAM;
import static com.ef.util.Const.THRESHOLD_PARAM;
import com.ef.util.DurationEnum;
import static com.ef.util.LogUtil.info;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Class to manage all logic business for Parser application
 * @author jdiaz86
 */
public final class Helper {

    //prevents from being instantized
    private Helper() {
    }
    
    /**
     * parsers access log file, creates log POJOs and persist them to database
     * @param param
     * @return
     */
    public static List<Log> readFile(Param param) {
        List<Log> logs = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(param.getAccessLog()))) {
            lines.forEach((line) -> {
                Log log = new Log();
                String[] elements = line.split(DELIMITER);
                try {
                    log.setDate(SDF_LOG.parse(elements[0]));
                } catch (ParseException ex) {
                    log.setDate(null);
                }
                log.setIp(elements[1]);
                log.setRequest(elements[2]);
                log.setStatus(elements[3]);
                log.setUserAgent(elements[4]);
                logs.add(log);
            });
            return logs;
        } catch (IOException e) {
            throw new ParserException(e.getMessage());
        }
    }

    /**
     * reads entry parameters and validates them
     * @param args
     * @return
     * @throws NoSuchElementException
     * @throws ParseException 
     */
    public static Param getParams(String... args) throws NoSuchElementException, ParseException {
        if (args.length != 4) {
            throw new NoSuchElementException();
        }
        List<String> list = Arrays.asList(args);
        Param param = new Param();

        param.setAccessLog(list.stream()
                .filter(arg -> arg.startsWith(ACCESS_LOG_PARAM))
                .findFirst().get().replaceFirst(ACCESS_LOG_PARAM, EMPTY));

        param.setStartDate(SDF_PARAM.parse(list.stream()
                .filter(arg -> arg.startsWith(START_DATE_PARAM))
                .findFirst().get().replaceFirst(START_DATE_PARAM, EMPTY)));

        param.setDuration(list.stream()
                .filter(arg -> arg.startsWith(DURATION_PARAM))
                .findFirst().get().replaceFirst(DURATION_PARAM, EMPTY));

        param.setThreshold(Long.valueOf(list.stream()
                .filter(arg -> arg.startsWith(THRESHOLD_PARAM))
                .findFirst().get().replaceFirst(THRESHOLD_PARAM, EMPTY)));

        if (!validateDuration(param.getDuration())) {
            throw new ParameterNotFoundException("--duration param can only be 'daily' or 'hourly'");
        }
        return param;
    }

    /**
     * validate if duration param is wether 'daily' or 'hourly'
     * @param durationParam
     * @return
     */
    private static boolean validateDuration(String durationParam) {
        EnumSet<DurationEnum> durations = EnumSet.allOf(DurationEnum.class);
        return durations.stream().anyMatch(duration -> duration.name().equalsIgnoreCase(durationParam));
    }
    
    /**
     * calculates time taken by Parser application to parser data
     * @param initialTime
     * @return 
     */
    private static Long calculateTimeInSecs(Long initialTime) {
        return (System.currentTimeMillis() - initialTime) / 1000;
    }
    
    /**
     * Copy all properties from one entity to another using reflection
     * @param <T1>
     * @param <T2>
     * @param from
     * @param to 
     */
    public static <T1 extends Serializable, T2 extends Serializable> void copy(T1 from, T2 to) {
        Class<? extends Serializable> fromClass = from.getClass();
        Class superFromClass = Serializable.class;
        Class<? extends Serializable> toClass = to.getClass();
        Field[] fromFields = fromClass.getDeclaredFields();
        Field[] superFromFields = superFromClass.getDeclaredFields();
        Field[] fields = ArrayUtils.addAll(fromFields, superFromFields);
        Object value;
        for (Field fromField : fields) {
            Field toField;
            try {
                toField = toClass.getDeclaredField(fromField.getName());
                toField.setAccessible(true);
                fromField.setAccessible(true);
                value = fromField.get(from);
                toField.set(to, value);
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
               
            }
        }
    }
    
    /**
     * header output for Parser application
     * @param param 
     */
    public static void printHeader(Param param) {
        info("******* RESULTS FOR THE PARSER APPLICATION ********");
        info("File parsed: {}", param.getAccessLog());
        info("Duration used: {}", param.getDuration());
        info("Threshold used: {}", param.getThreshold());
        info("Start date used: {}",param.getStartDate());
        info("****************************************************");
    }
    
    /**
     * outputs results if found
     * @param result 
     */
    public static void printResultToScreen(Result result) {
        info("IP found: {}", result.getIp());
        info("Requests found in log file: {}", result.getTimes());
        info("Comments on why it's blocked: ");
        info(result.getComments());
        info("***************************************************");
    }
    
    /**
     * outputs time taken by Parser application
     * @param runtime 
     */
    public static void printTime(Long runtime) {
        info("******** all task completed in {} seconds. ********", calculateTimeInSecs(runtime));
    }

}
