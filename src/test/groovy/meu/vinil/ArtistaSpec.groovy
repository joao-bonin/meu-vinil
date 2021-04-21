package meu.vinil

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class ArtistaSpec extends Specification implements DomainUnitTest<Artista> {

    void "Realiza a validacao do titulo"() {
        when:
        domain.nome = valor

        then:
        !domain.validate(['nome'])
        domain.errors['nome'].code == resultado

        where:
        valor     || resultado
        null      || 'nullable'
        ""        || 'blank'
        'a' * 256 || 'maxSize.exceeded'
    }
}
