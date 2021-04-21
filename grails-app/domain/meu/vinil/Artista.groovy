package meu.vinil

/**
 * Dominio referente a classe de Artistas.
 *
 * @author Manolo
 */
class Artista implements Serializable{

    private static final long serialVersionUID = -295422703255886286L;

    String nome

    static belongsTo = [album: Album, genero: Genero]

    static constraints = {
        nome nullable: false, blank: false, maxSize: 255
    }

    String toString(){
        this.nome
    }
}
