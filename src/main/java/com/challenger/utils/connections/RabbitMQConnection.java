package com.challenger.utils.connections;

import com.challenger.utils.constants.RabbitmqConstans;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConnection {
    private static final String NAME_EXCHANGE = "amq.direct";
    @Autowired
    private AmqpAdmin amqpAdmin;

    public RabbitMQConnection(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }


    private Queue rowQueue(String rowName) {return new Queue(rowName, true, false, false);}

    private DirectExchange tradeDirect(){
        return new DirectExchange(NAME_EXCHANGE);
    }

    private Binding relation(Queue row, DirectExchange trade){
        return new Binding(row.getName(), Binding.DestinationType.QUEUE, trade.getName(), row.getName(), null);
    }

    @PostConstruct
    private void add(){
       Queue rowVote = this.rowQueue(RabbitmqConstans.ROW_VOTE);

       DirectExchange trade = this.tradeDirect();

       Binding callRowVote = this.relation(rowVote, trade);

       //creating row in RabbitMQ
       this.amqpAdmin.declareQueue(rowVote);

       this.amqpAdmin.declareExchange(trade);

       this.amqpAdmin.declareBinding(callRowVote);
    }

}
