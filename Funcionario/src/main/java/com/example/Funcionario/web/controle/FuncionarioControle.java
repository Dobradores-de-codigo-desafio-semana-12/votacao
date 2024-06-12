package com.example.Funcionario.web.controle;

import com.example.Funcionario.entidade.Funcionario;
import com.example.Funcionario.servico.FuncionarioServico;
import com.example.Funcionario.web.dto.FuncionarioCriarDto;
import com.example.Funcionario.web.dto.FuncionarioEmailDto;
import com.example.Funcionario.web.dto.FuncionarioRespostaDto;
import com.example.Funcionario.web.dto.mapa.FuncionarioMapa;
import com.example.Funcionario.web.exception.ErrorMessage;
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
@Tag(name = "Funcionários", description = "Contém todas as operações relativas aos recursos para cadastro, edição e leitura de um funcionário.")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/funcionarios")
public class FuncionarioControle {

    private final FuncionarioServico funcionarioServico;

    @Operation(summary = "Cadastrar um novo funcionário", description = "Recurso para criar um novo funcionário",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = FuncionarioRespostaDto.class))),
                    @ApiResponse(responseCode = "400", description = "Recurso não processado por dados de entrada inválidos",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PostMapping
    public ResponseEntity<FuncionarioRespostaDto> cadastrarFuncionario(@Valid @RequestBody FuncionarioCriarDto dto){
        Funcionario fun = funcionarioServico.salvar(FuncionarioMapa.paraFuncionario(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(FuncionarioMapa.paraDto(fun));
    }
    @Operation(summary = "Recuperar um funcionário pelo id", description = "Recurso para recuperar um funcionário pelo seu identificador único",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = FuncionarioRespostaDto.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioRespostaDto> buscarFuncionario(@PathVariable Long id){
        Funcionario fun = funcionarioServico.buscarPorId(id);
        return ResponseEntity.ok(FuncionarioMapa.paraDto(fun));
    }
    @Operation(summary = "Atualizar email de um funcionário", description = "Recurso para atualizar o email de um funcionário",
            responses = {
                    @ApiResponse(responseCode = "202", description = "Email atualizado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = FuncionarioRespostaDto.class))),
                    @ApiResponse(responseCode = "400", description = "Recurso não processado por dados de entrada inválidos",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PatchMapping("/email/{id}")
    public ResponseEntity<FuncionarioRespostaDto> atualizarEmail(@PathVariable Long id, @RequestBody FuncionarioEmailDto dto) {
        Funcionario fun = funcionarioServico.editarEmail(id, dto.getNovoEmail());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(FuncionarioMapa.paraDto(fun));
    }
    @Operation(summary = "Desabilitar um funcionário", description = "Recurso para desabilitar um funcionário",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Funcionário desabilitado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PatchMapping("/desabilitar/{id}")
    public ResponseEntity<Void> desabilitarFuncionario(@PathVariable Long id) {
        funcionarioServico.desabilitarFuncionario(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Listar todos os funcionários", description = "Recurso para listar todos os funcionários",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de funcionários recuperada com sucesso",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = FuncionarioRespostaDto.class)))),
                    @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @GetMapping
    public ResponseEntity<List<FuncionarioRespostaDto>> getAll() {
        List<Funcionario> fun = funcionarioServico.buscarTodos();
        return ResponseEntity.ok(FuncionarioMapa.paraListaDto(fun));
    }
}
