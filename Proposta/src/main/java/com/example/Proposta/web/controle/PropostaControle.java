package com.example.Proposta.web.controle;

import com.example.Proposta.entidade.Proposta;
import com.example.Proposta.servico.PropostaServico;
import com.example.Proposta.web.dto.PropostaCriarDto;
import com.example.Proposta.web.dto.PropostaRespostaDto;
import com.example.Proposta.web.dto.mapa.PropostaMapa;
import com.example.Proposta.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Propostas", description = "Contém todas as operações relativas aos recursos para cadastro, edição e leitura de uma proposta.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/propostas")
public class PropostaControle {

    private final PropostaServico propostaServico;

    @Operation(summary = "Cadastrar uma nova proposta", description = "Recurso para criar uma nova proposta",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PropostaRespostaDto.class))),
                    @ApiResponse(responseCode = "400", description = "Recurso não processado por dados de entrada inválidos",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PostMapping
    public ResponseEntity<PropostaRespostaDto> cadastrarProposta(@Valid @RequestBody PropostaCriarDto dto){
        Proposta proposta = propostaServico.salvar(PropostaMapa.paraProposta(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(PropostaMapa.paraDto(proposta));
    }

    @Operation(summary = "Recuperar uma proposta pelo id", description = "Recurso para recuperar uma proposta pelo seu identificador único",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PropostaRespostaDto.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @GetMapping("/{id}")
    public ResponseEntity<PropostaRespostaDto> buscarProposta(@PathVariable Long id){
        Proposta proposta = propostaServico.buscarPorId(id);
        return ResponseEntity.ok(PropostaMapa.paraDto(proposta));
    }


    @Operation(summary = "Desabilitar uma proposta", description = "Recurso para desabilitar uma proposta",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Proposta desabilitada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PatchMapping("/desabilitar/{id}")
    public ResponseEntity<Void> desabilitarProposta(@PathVariable Long id) {
        propostaServico.desabilitarProposta(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Listar todas as propostas", description = "Recurso para listar todas as propostas",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de propostas recuperada com sucesso",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = PropostaRespostaDto.class)))),
                    @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @GetMapping
    public ResponseEntity<List<PropostaRespostaDto>> getAll() {
        List<Proposta> propostas = propostaServico.buscarTodos();
        return ResponseEntity.ok(PropostaMapa.paraListaDto(propostas));
    }

}
