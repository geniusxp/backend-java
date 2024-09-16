package br.com.fiap.sprintjava.models;

import br.com.fiap.sprintjava.dtos.event.CreateEventDTO;
import br.com.fiap.sprintjava.dtos.event.UpdateEventDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "T_GXP_EVENT")
@Data
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue
    @Column(name="id_event")
    private Long id;

    @Column(name="nm_event")
    private String name;

    @Column(name="tx_description")
    private String description;

    @Column(name="ds_event_type")
    private String eventType;

    @Column(name="url_image")
    private String imageUrl;

    @OneToMany(mappedBy = "event")
    private List<Coupon> coupons;

    @OneToMany(mappedBy = "event")
    private List<TicketType> ticketTypes;

    @OneToMany(mappedBy = "event")
    private List<EventDay> eventDays;

    public Event(CreateEventDTO dto){
        name = dto.name();
        description = dto.description();
        eventType = dto.eventType();
        imageUrl = dto.imageUrl();
    }

    public void atualizar(UpdateEventDTO dto){
        if(dto.name() != null)
            this.name = dto.name();
        if(dto.description() != null)
            this.description = dto.description();
        if(dto.eventType() != null)
            this.eventType = dto.eventType();
        if(dto.imageUrl() != null)
            this.imageUrl = dto.imageUrl();
    }
}
