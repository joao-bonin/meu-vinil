package meu.vinil

/**
 * Dominio reerente a Generos dos Artistas.
 */
class Genero{

    String titulo

    static hasMany = [artista: Artista]

    static constraints = {
        titulo nullable: false, blank: false, maxSize: 128, unique: true
    }

    String toString() {
        this.titulo
    }

}
