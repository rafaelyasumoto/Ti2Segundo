public class Matricula {
    private String cor;
    private int placa;
    private String ano;
    private int matricula;
    
    public Matricula() {
        this.cor = "";
        this.placa = -1;
        this.ano = "";
        this.matricula = -1;
    }
    
    public Matricula(String cor, int placa, String ano, int matricula) {
        this.cor = cor;
        this.placa = placa;
        this.ano = ano;
        this.matricula = matricula;
    }

    public String getTipo() {
        return cor;
    }

    public void setTipo(String cor) {
        this.cor = cor;
    }

    public int getPlaca() {
        return placa;
    }

    public void setPlaca(int placa) {
        this.placa = placa;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return "Matricula [cor=" + cor + ", placa=" + placa + ", ano=" + ano + ", matricula=" + matricula + "]";
    }    
}
