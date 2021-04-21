package meu.vinil

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional
import grails.rest.*
import grails.converters.*
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK

@ReadOnly
class ArtistaController {

    ArtistaService artistaService

    static responseFormats = ['json']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond artistaService.list(params), model: [artistaCount: artistaService.count()]
    }

    def show(Long id) {
        respond artistaService.get(id)
    }

    @Transactional
    def save(Artista artista) {
        if (artista == null) {
            render status: NOT_FOUND
            return
        }
        if (artista.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond artista.errors
            return
        }

        try {
            artistaService.save(artista)
            envioService.converteEnviaMensagem(artista)
        } catch (ValidationException e) {
            respond artista.errors
            return
        }

        respond artista, [status: CREATED]
    }

    @Transactional
    def update(Artista artista) {
        if (artista == null) {
            render status: NOT_FOUND
            return
        }
        if (artista.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond artista.errors
            return
        }

        try {
            artistaService.save(artista)
        } catch (ValidationException e) {
            respond artista.errors
            return
        }

        respond artista, [status: OK]
    }

    @Transactional
    def delete(Long id) {
        if (id == null || artistaService.delete(id) == null) {
            render status: NOT_FOUND
            return
        }

        render status: NO_CONTENT
    }
}
