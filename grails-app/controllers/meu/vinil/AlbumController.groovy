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
class AlbumController {

    AlbumService albumService

    static responseFormats = ['json']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond albumService.list(params), model: [albumCount: albumService.count()]
    }

    def show(Long id) {
        respond albumService.get(id)
    }

    @Transactional
    def save(Album album) {
        if (album == null) {
            render status: NOT_FOUND
            return
        }
        if (album.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond album.errors
            return
        }

        try {
            albumService.save(album)
        } catch (ValidationException e) {
            respond album.errors
            return
        }

        respond album, [status: CREATED]
    }

    @Transactional
    def update(Album album) {
        if (album == null) {
            render status: NOT_FOUND
            return
        }
        if (album.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond album.errors
            return
        }

        try {
            albumService.save(album)
        } catch (ValidationException e) {
            respond album.errors
            return
        }

        respond album, [status: OK]
    }

    @Transactional
    def delete(Long id) {
        if (id == null || albumService.delete(id) == null) {
            render status: NOT_FOUND
            return
        }

        render status: NO_CONTENT
    }
}
