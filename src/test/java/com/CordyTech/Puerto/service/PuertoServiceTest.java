package com.CordyTech.Puerto.service;

import com.CordyTech.Puerto.dto.PuertoDto;
import com.CordyTech.Puerto.exception.ResourceNotFoundException;
import com.CordyTech.Puerto.model.Puerto;
import com.CordyTech.Puerto.repository.PuertoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PuertoServiceTest {

    @Mock
    private PuertoRepository repository;

    @InjectMocks
    private PuertoService service;

    private Puerto puerto;
    private PuertoDto puertoDto;

    @BeforeEach
    void setUp() {
        puerto = new Puerto();
        puerto.setIdPuerto(1);
        puerto.setNombrePuerto("Puerto Montt");
        puerto.setTarifaHora(15.0f);
        puerto.setTarifaEslora(5.0f);
        puerto.setDispo(true);

        puertoDto = new PuertoDto();
        puertoDto.setNombrePuerto("Puerto Montt");
        puertoDto.setTarifaHora(15.0f);
        puertoDto.setTarifaEslora(5.0f);
        puertoDto.setDispo(true);
    }

    @Test
    void listarDto_debeRetornarListaDeDto() {
        when(repository.findAll()).thenReturn(List.of(puerto));

        List<PuertoDto> resultado = service.listarDto();

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getNombrePuerto()).isEqualTo("Puerto Montt");
    }

    @Test
    void obtenerDtoPorId_cuandoExiste_debeRetornarDto() {
        when(repository.findById(1)).thenReturn(Optional.of(puerto));

        PuertoDto resultado = service.obtenerDtoPorId(1);

        assertThat(resultado.getNombrePuerto()).isEqualTo("Puerto Montt");
        assertThat(resultado.getTarifaHora()).isEqualTo(15.0f);
    }

    @Test
    void obtenerDtoPorId_cuandoNoExiste_debeLanzar404() {
        when(repository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.obtenerDtoPorId(99))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void guardarDto_debeGuardarYRetornarDto() {
        when(repository.save(any(Puerto.class))).thenReturn(puerto);

        PuertoDto resultado = service.guardarDto(puertoDto);

        assertThat(resultado.getNombrePuerto()).isEqualTo("Puerto Montt");
        verify(repository, times(1)).save(any(Puerto.class));
    }

    @Test
    void actualizarDto_cuandoExiste_debeActualizarYRetornarDto() {
        when(repository.findById(1)).thenReturn(Optional.of(puerto));
        when(repository.save(any(Puerto.class))).thenReturn(puerto);

        PuertoDto dtoActualizado = new PuertoDto();
        dtoActualizado.setNombrePuerto("Puerto Varas");
        dtoActualizado.setTarifaHora(20.0f);
        dtoActualizado.setTarifaEslora(8.0f);
        dtoActualizado.setDispo(false);

        PuertoDto resultado = service.actualizarDto(1, dtoActualizado);

        assertThat(resultado).isNotNull();
        verify(repository, times(1)).save(any(Puerto.class));
    }

    @Test
    void actualizarDto_cuandoNoExiste_debeLanzar404() {
        when(repository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.actualizarDto(99, puertoDto))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");

        verify(repository, never()).save(any());
    }

    @Test
    void eliminar_cuandoExiste_debeLlamarDeleteById() {
        when(repository.existsById(1)).thenReturn(true);
        doNothing().when(repository).deleteById(1);

        service.eliminar(1);

        verify(repository, times(1)).deleteById(1);
    }

    @Test
    void eliminar_cuandoNoExiste_debeLanzar404() {
        when(repository.existsById(99)).thenReturn(false);

        assertThatThrownBy(() -> service.eliminar(99))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
    }
}
