package br.unigran.agenda;

public class Contato {
    private String nome;
    private String telefone;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public String getTelefone() {

        return telefone;
    }

    public void setTelefone(String telefone) {

        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "nome= " + nome + '\'' +
                "telefone= " + telefone + '\'';
    }
}
