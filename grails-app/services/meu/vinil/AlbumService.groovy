package meu.vinil

import grails.gorm.services.Service

@Service(Album)
interface AlbumService {

    Album get(Serializable id)

    List<Album> list(Map args)

    Long count()

    Album delete(Serializable id)

    Album save(Album album)

}
