package br.com.fiap.sprintjava.event;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table (name = "T_GXP_EVENT")
@Data
public class Event {

    @Id
    int id_event;
    String nm_event;
    String tx_description;
    String ds_event_type;
    String url_image;
}
