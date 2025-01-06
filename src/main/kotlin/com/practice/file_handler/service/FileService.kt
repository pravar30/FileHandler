package com.practice.file_handler.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.net.URL
import java.nio.file.Files
import java.nio.file.Paths


@Service
class FileService {

    @Value("\${file_save_location}")
    private lateinit var fileSaveLocation: String

    fun upload(file: MultipartFile) {

        val newFile = File("$fileSaveLocation/${file.originalFilename}")

        FileOutputStream(newFile).use { os ->
            os.write(file.bytes)
        }
    }

    fun download(fileName: String) {

        val inputStream = URL("$fileSaveLocation/$fileName").openStream()
//        val inputStream: InputStream = URL("$fileSaveLocation/$fileName").openStream()
        Files.copy(inputStream, Paths.get(fileName))

    }
}