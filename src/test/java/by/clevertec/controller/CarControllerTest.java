package by.clevertec.controller;

import by.clevertec.domain.Car;
import by.clevertec.exception.CarServiceException;
import by.clevertec.service.CarService;
import by.clevertec.util.TestData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
class CarControllerTest {

    @MockBean
    private CarService carService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldFindAll() throws Exception {
        //given
        when(carService.getCars())
                .thenReturn(TestData.generateCarList());
        //when
        mockMvc.perform(get("/api/cars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
        //then

    }

    @Test
    void shouldFindById() throws Exception {
        //given
        Car car = TestData.generateCar();
        when(carService.getCarById(car.getUuid()))
                .thenReturn(car);
        //when then
        mockMvc.perform(get("/api/car/{id}", car.getUuid()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid").value(car.getUuid().toString()))
                .andExpect(jsonPath("$.body").value(car.getBody().toString()));

    }

    @Test
    void shouldReturnStatus404anExceptionThrownWhenFindById() throws Exception {
        //given when then
        mockMvc.perform(get("/api/car/{id}", UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateNewCar() throws Exception {
        //given
        Car car = TestData.generateCar();
        when(carService.createCar(car))
                .thenReturn(car);
        //when then
        mockMvc.perform(post("/api/car/new")
                        .content(objectMapper.writeValueAsString(car))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.uuid").value(car.getUuid().toString()))
                .andExpect(jsonPath("$.body").value(car.getBody().toString()));
    }

    @Test
    void shouldUpdateCar() throws Exception {
        //given
        Car car = TestData.generateCar();
        when(carService.updateCar(car.getUuid(), car))
                .thenReturn(car);
        //when then
        mockMvc.perform(put("/api/car/update/{id}", car.getUuid())
                        .content(objectMapper.writeValueAsString(car))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid").value(car.getUuid().toString()))
                .andExpect(jsonPath("$.body").value(car.getBody().toString()));
    }

    @Test
    void shouldDeleteCar() throws Exception {
        // given
        UUID uuid = UUID.randomUUID();

        // when then
        mockMvc.perform(delete("/api/car/delete/{id}", uuid))
                .andExpect(status().isOk());
    }


}