package it.solvingteam.springmvcrestdemo.controller;

import it.solvingteam.springmvcrestdemo.dto.ErrorDto;
import it.solvingteam.springmvcrestdemo.dto.OperationFactorsDto;
import it.solvingteam.springmvcrestdemo.dto.ResultDto;
import it.solvingteam.springmvcrestdemo.service.TestService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestController
@RequestMapping("math")
public class MathController {

    @Autowired
    private TestService testService;

    @GetMapping("sum")
    public ResponseEntity<ResultDto> sum(@RequestParam Integer a, @RequestParam Integer b) {
        ResultDto resultDto = new ResultDto();
        resultDto.setResult(a+b);

        if(resultDto.getResult() < 10){
            throw new RuntimeException("AAAAA");
        }

        testService.getById();

        return ResponseEntity.status(HttpStatus.OK).body(resultDto);
    }

    @PostMapping("substraction")
    public ResponseEntity<ResultDto> substraction(@Valid @RequestBody OperationFactorsDto operationFactorsDto, BindingResult bindingResult) {
        ResultDto resultDto = new ResultDto();
        resultDto.setResult(operationFactorsDto.getA() - operationFactorsDto.getB());

        return ResponseEntity.status(HttpStatus.OK).body(resultDto);
    }

    @PostMapping("testPathVariable")
    public ResponseEntity<ResultDto> substraction(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorDto> handleException(MethodArgumentTypeMismatchException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(e.getMessage()));
    }

}
