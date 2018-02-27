package com.ef.service.impl;

import com.ef.exception.ParamDateFormatException;
import com.ef.exception.ParamThresholdException;
import com.ef.exception.ParameterNotFoundException;
import com.ef.helper.Helper;
import com.ef.model.Log;
import com.ef.model.LogDto;
import com.ef.model.Param;
import com.ef.model.Result;
import com.ef.service.LogService;
import com.ef.service.ParserService;
import com.ef.service.ResultService;
import com.ef.util.DurationEnum;
import static com.ef.util.LogUtil.info;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Main service for Parser application
 * @author jdiaz86
 */
public class ParserServiceImpl implements ParserService {

    LogService logService = new LogServiceImpl();
    ResultService resultService = new ResultServiceImpl();

    @Override
    public void run(String... args) {
        Long runtime = System.currentTimeMillis();
        parser(args);
        Helper.printTime(runtime);
    }

    @Override
    public void printVersion() {
        logService.printVersion();
    }
    
    private void parser(String... args) {
        try {
            Param param = Helper.getParams(args);
            List<Log> logs = Helper.readFile(param);
            logService.removeAll();
            logService.saveBatch(logs);
            LocalDateTime from = LocalDateTime.ofInstant(param.getStartDate().toInstant(), ZoneId.systemDefault());
            LocalDateTime to;
            if (DurationEnum.DAILY.name().equalsIgnoreCase(param.getDuration())) {
                to = from.plusDays(1);
            } else {
                to = from.plusHours(1);
            }
            List<LogDto> list = logService.process(from, to, param.getThreshold());
            Helper.printHeader(param);
            if (!list.isEmpty()) {
                list.forEach((LogDto logDto) -> {
                    Result result = new Result();
                    logDto.setSavedDate(new Date());
                    logDto.setComments(
                            String.format("The IP %s has a threshold of %d which is the result why is blocked, dates evaluated from %s to %s", 
                            logDto.getIp(), logDto.getTimes(), from, to));
                    Helper.copy(logDto, result);
                    resultService.save(result);
                    Helper.printResultToScreen(result);
                });                
            } else {
                info("****************** NO DATA FOUND ***********************");
            }
        } catch (ParameterNotFoundException e) {
            throw new ParameterNotFoundException(e.getMessage());
        } catch (NoSuchElementException e) {
            throw new ParameterNotFoundException();
        } catch (ParseException e) {
            throw new ParamDateFormatException();
        } catch (NumberFormatException e) {
            throw new ParamThresholdException();
        }
    }

}
