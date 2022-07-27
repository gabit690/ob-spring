package com.gabit.obpractice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String showHome() {
        return """
                <!doctype html>
                <html lang="en">
                  <head>
                    <meta charset="utf-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <title>Bootstrap demo</title>
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
                  </head>
                  <body class="text-bg-info">
                    <main class="container text-bg-light p-3 mt-5">
                        <h1 class="text-center fs-1 fw-bolder">Practice - Video game API</h1>
                        <h2>Requirements</h2>
                        <ul>
                            <li><a href="https://www.postman.com/" target="_blank" rel="noopener noreferrer">Postman</a></li>
                        </ul>
                        <h2>Documentation</h2>
                        <p>See more info and documentation <a href="http://localhost:8080/swagger-ui/" target="_blank" rel="noopener noreferrer">here</a>.</p>
                    </main>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
                  </body>
                </html>
                """;
    }
}
