package model;

import java.time.LocalDate;

public class Emprunt {
    private int id;
    private int idMenbre, idLivre;
    private LocalDate dateEmprunt, dateRetour;

    public Emprunt(){}

    public Emprunt(int id, int idMenbre, int idLivre, LocalDate dateEmprunt, LocalDate dateRetour) {
        this.id = id;
        this.idMenbre = idMenbre;
        this.idLivre = idLivre;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMenbre() {
        return idMenbre;
    }

    public void setIdMenbre(int idMenbre) {
        this.idMenbre = idMenbre;
    }

    public int getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDate getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(LocalDate dateRetour) {
        this.dateRetour = dateRetour;
    }

    @Override
    public String toString() {
        return "Emprunt{" +
                "id=" + id +
                ", idMenbre=" + idMenbre +
                ", idLivre=" + idLivre +
                ", dateEmprunt=" + dateEmprunt +
                ", dateRetour=" + dateRetour +
                '}';
    }
}
