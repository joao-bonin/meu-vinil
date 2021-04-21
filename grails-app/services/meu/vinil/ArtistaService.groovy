package meu.vinil

import grails.gorm.services.Service

@Service(Artista)
interface ArtistaService {

    Artista get(Serializable id)

    List<Artista> list(Map args)

    Long count()

    Artista delete(Serializable id)

    Artista save(Artista artista)

}
