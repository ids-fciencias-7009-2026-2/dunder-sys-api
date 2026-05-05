package com.dunder.mifflin.paper.dunderSys.adapters

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service

@Service
class InternalMailAdapter(
    // Inyectamos el Bean que Spring Boot crea automáticamente
    // basándose en las propiedades que pusiste en application.properties
    private val mailSender: JavaMailSender
) {

    @Value("\${internal.email.from}")
    lateinit var fromEmail: String

    private val logger = LoggerFactory.getLogger(InternalMailAdapter::class.java)

    fun sendEmail(to: String, subject: String, body: String): Result<Any> {
        logger.info("Sending email to $to using Spring Mail...")

        // Creamos un objeto de mensaje simple
        val message = SimpleMailMessage().apply {
            from = fromEmail
            setTo(to)
            setSubject(subject)
            text = body
        }

        return try {
            // Usamos el objeto mailSender que ya está configurado globalmente.
            mailSender.send(message)

            logger.info("Successfully sent email to $to...")
            Result.success(Any())
        } catch (e: Exception) {
            // Capturamos Exception genérica o MailException de Spring
            logger.error("Error sending email via SMTP", e)
            Result.failure(e)
        }
    }

    fun sendHtmlEmail(to: String, subject: String, htmlBody: String): Result<Any> {
        val mimeMessage = mailSender.createMimeMessage()

        // El parámetro 'true' indica que el mensaje es "multipart" (permite adjuntos y HTML)
        val helper = MimeMessageHelper(mimeMessage, true, "UTF-8")

        return try {
            helper.setFrom(fromEmail)
            helper.setTo(to)
            helper.setSubject(subject)

            helper.setText(htmlBody, true)

            logger.info("Enviando correo HTML a $to...")
            mailSender.send(mimeMessage)

            Result.success(true)
        } catch (e: Exception) {
            logger.error("Fallo al enviar correo HTML", e)
            Result.failure(e)
        }
    }
}