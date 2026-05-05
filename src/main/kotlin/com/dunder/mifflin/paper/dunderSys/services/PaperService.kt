package com.dunder.mifflin.paper.dunderSys.services

import com.dunder.mifflin.paper.dunderSys.adapters.InternalMailAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PaperService {

    @Autowired
    private lateinit var internalMailAdapter: InternalMailAdapter

    fun notifyOfferToSellers(to: String, paperType: String, discount: Int, extraNotes: String): Result<Any> {
        val body =
            "We will give an offer for $paperType . This paper type will be with $discount% discount. \nPLEASE REMEMBER: $extraNotes"
        val subject = "[INTERNAL] Offer for $paperType"
        return internalMailAdapter.sendEmail(to, subject, body)
    }


    fun notifyOfferToSellersHTML(to: String, paperType: String, discount: Int, extraNotes: String): Result<Any> {
        val htmlTemplate = """
    <div style="font-family: Arial, sans-serif; border: 1px solid #ccc; padding: 20px;">
        <h1 style="color: #0047bb;">Dunder Mifflin Paper Co.</h1>
        <p>Hola, <strong>$to</strong>,</p>
        <p>Se ha generado una nueva oferta para $paperType</p>
        <p>Ofreceremos un descuento de $discount%</p>
        
        <p><b>IMPORTANTE:</b> $extraNotes<p>
        <hr>
        <footer style="font-size: 0.8em; color: #777;">
            Este es un correo automático, por favor no responder.
        </footer>
    </div>
""".trimIndent()
        val subject = "[INTERNAL] Offer for $paperType"

        return internalMailAdapter.sendHtmlEmail(to, subject, htmlTemplate)
    }
}