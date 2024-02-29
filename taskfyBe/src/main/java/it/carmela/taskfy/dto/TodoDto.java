package it.carmela.taskfy.dto;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TodoDto {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private boolean completed;

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private LocalDateTime dateExpired;

    public expireState expireStateVar;

    public LocalDateTime getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(LocalDateTime dateExpired) {
        this.dateExpired = dateExpired;
    }

    public enum expireState {
        scaduta, inScadenza, inTempo
    }
    public LocalDateTime dateCreate;

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }


    public void calcolaRange() {

        LocalDateTime today = LocalDateTime.now();
    
        // Calcolare la differenza in ore tra le date
        long hoursRemaining = today.until(dateExpired, ChronoUnit.HOURS);

        if (hoursRemaining > 30) {
            expireStateVar = expireState.inTempo;
        } else if (hoursRemaining < 30 && hoursRemaining>0) {
            expireStateVar = expireState.inScadenza;
        } else {
            expireStateVar = expireState.scaduta;
        }
    
    }

}
