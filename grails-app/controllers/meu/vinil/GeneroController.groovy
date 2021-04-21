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
class GeneroController {

    GeneroService generoService

    static responseFormats = ['json']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond generoService.list(params), model: [generoCount: generoService.count()]
    }

    def show(Long id) {
        respond generoService.get(id)
    }

    @Transactional
    def save(Genero genero) {
        if (genero == null) {
            render status: NOT_FOUND
            return
        }
        if (genero.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond genero.errors
            return
        }

        try {
            generoService.save(genero)
        } catch (ValidationException e) {
            respond genero.errors
            return
        }

        respond genero, [status: CREATED]
    }

    @Transactional
    def update(Genero genero) {
        if (genero == null) {
            render status: NOT_FOUND
            return
        }
        if (genero.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond genero.errors
            return
        }

        try {
            generoService.save(genero)
        } catch (ValidationException e) {
            respond genero.errors
            return
        }

        respond genero, [status: OK]
    }

    @Transactional
    def delete(Long id) {
        if (id == null || generoService.delete(id) == null) {
            render status: NOT_FOUND
            return
        }

        render status: NO_CONTENT
    }
}
