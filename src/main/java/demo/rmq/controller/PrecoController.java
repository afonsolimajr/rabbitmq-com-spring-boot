package demo.rmq.controller;

import demo.rmq.consts.RabbitMQConsts;
import demo.rmq.dto.PrecoDto;
import demo.rmq.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="preco")
public class PrecoController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @PutMapping
    private ResponseEntity alteraPreco(@RequestBody PrecoDto precoDto){
        System.out.println(precoDto.codigoProduto);

        this.rabbitMQService.enviaMensagem(RabbitMQConsts.FILA_PRECO, precoDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
