package meu.vinil

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class AlbumSpec extends Specification implements DomainUnitTest<Album> {

    void "Realiza a validacao do titulo"() {
        when:
        domain.titulo = valor

        then:
        !domain.validate(['titulo'])
        domain.errors['titulo'].code == resultado

        where:
        valor     || resultado
        null      || 'nullable'
        ""        || 'blank'
        'a' * 401 || 'maxSize.exceeded'
    }
}
