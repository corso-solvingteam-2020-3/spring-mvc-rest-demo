package it.solvingteam.springmvcrestdemo.controller;

import it.solvingteam.springmvcrestdemo.dto.ErrorDto;
import it.solvingteam.springmvcrestdemo.dto.OperationFactorsDto;
import it.solvingteam.springmvcrestdemo.dto.ResultDto;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pippo")
public class PippoController {

    @GetMapping("sum")
    public ResponseEntity<ResultDto> sum(@RequestParam Integer a, @RequestParam Integer b) {
        ResultDto resultDto = new ResultDto();
        resultDto.setResult(a+b);

        return ResponseEntity.status(HttpStatus.OK).body(resultDto);
    }

    @PostMapping("substraction")
    public ResponseEntity<ResultDto> substraction(@Valid @RequestBody OperationFactorsDto operationFactorsDto, BindingResult bindingResult) {
        ResultDto resultDto = new ResultDto();
        resultDto.setResult(operationFactorsDto.getA() - operationFactorsDto.getB());

        return ResponseEntity.status(HttpStatus.OK).body(resultDto);
    }


}
