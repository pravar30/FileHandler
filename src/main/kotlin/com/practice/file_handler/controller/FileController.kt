package com.practice.file_handler.controller

import com.practice.file_handler.service.FileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.InputStream
import java.net.URL


@RestController
class FileController {

    @Autowired
    private lateinit var fileService: FileService

    @PostMapping("/upload")
    fun upload(@RequestParam("file") file: MultipartFile?): ResponseEntity<String> {

        if(file == null)
            return ResponseEntity<String>("File not found", HttpStatus.NOT_FOUND)
        fileService.upload(file)

        return ResponseEntity<String>("File saved successfully", HttpStatus.CREATED)
    }

    @GetMapping("/download/{fileName}")
    fun download(@PathVariable("fileName") fileName: String): ResponseEntity<String> {

        fileService.download(fileName)

        return ResponseEntity("", HttpStatus.OK)
    }
}