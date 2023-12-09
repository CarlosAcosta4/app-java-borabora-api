package pe.BoraBora.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.BoraBora.response.ApiResponse;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/test")
    public ResponseEntity<ApiResponse> getTest() {
        return new ResponseEntity<>(new ApiResponse("Test exitoso", HttpStatus.OK), HttpStatus.OK);
    }
}

