package com.example.Proposta.web.controle;



import com.example.Proposta.entidade.Proposta;
import com.example.Proposta.entidade.Voto;
import com.example.Proposta.servico.VotoServico;

import com.example.Proposta.web.dto.VotoCriarDto;
import com.example.Proposta.web.dto.VotoRespostaDto;
import com.example.Proposta.web.dto.mapa.PropostaMapa;
import com.example.Proposta.web.dto.mapa.VotoMapa;
import com.example.Proposta.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Tag(name = "Votos", description = "Contém todas as operações relativas aos recursos para iniciar voto ou listar todos os votos.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/votos")
public class VotoControle {

    private final VotoServico votoServico;

    @Operation(summary = "Iniciar votos para uma proposta", description = "Recurso para iniciar votos para uma proposta",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Votos iniciados com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PatchMapping("/proposta/{id}")
    public ResponseEntity<Void> iniciarVotos(@PathVariable Long id){
        votoServico.iniciarVotos(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Listar todos os votos", description = "Recurso para listar todos os votos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de votos recuperada com sucesso",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = VotoRespostaDto.class)))),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PostMapping()
    public ResponseEntity<VotoRespostaDto> votar(@RequestBody VotoCriarDto dto){
       VotoRespostaDto respostaDto = votoServico.votar(dto);
        return ResponseEntity.ok(respostaDto);
    }

}
