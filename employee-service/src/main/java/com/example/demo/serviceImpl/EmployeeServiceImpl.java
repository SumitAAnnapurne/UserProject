package com.example.demo.serviceImpl;

import org.springframework.stereotype.Service;

import com.example.demo.dto.APIResponseDto;
import com.example.demo.dto.DepartmentDto;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.Employee;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.APIClient;
import com.example.demo.service.EmployeeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	
	//private RestTemplate restTemplate;
	
	//@Autowired
	//private WebClient webClient;
	
	private APIClient apiClient;

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		Employee mapToEmployee = EmployeeMapper.mapToEmployee(employeeDto);

		Employee save = employeeRepository.save(mapToEmployee);

		return EmployeeMapper.mapToEmployeeDto(save);
	}

	@Override
	public APIResponseDto getEmployeeById(Long id) {
		Employee byId = employeeRepository.findById(id).get();
		
//		ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" + byId.getDepartmentCode() , DepartmentDto.class);
//			
//		DepartmentDto departmentDto = responseEntity.getBody();
		
//		DepartmentDto departmentDto =webClient.get()
//													.uri("http://localhost:8080/api/departments/" + byId.getDepartmentCode())
//													.retrieve()
//													.bodyToMono(DepartmentDto.class)
//													.block();
		
		DepartmentDto departmentDto = apiClient.getDepartment(byId.getDepartmentCode());
		
		EmployeeDto mapToEmployeeDto = EmployeeMapper.mapToEmployeeDto(byId);
		
		APIResponseDto apiResponseDto = new APIResponseDto();
		apiResponseDto.setEmployee(mapToEmployeeDto);
		apiResponseDto.setDepartment(departmentDto);
		
		
		return apiResponseDto;
	}

}
