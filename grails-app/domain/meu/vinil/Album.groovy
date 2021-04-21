package meu.vinil

/**
 * Dominio referente a classe de Albuns.
 *
 * @author Manolo
 */
class Album {

    String titulo

    Integer anoLancamento

    static hasMany = [artista: Artista]

    static constraints = {
        titulo nullable: false, blank: false, maxSize: 400, unique: true
    }

    String toString() {
        this.titulo
        this.anoLancamento
    }
}
