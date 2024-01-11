# Medical Appointment Schedule

Medical Appointment Schedule é um simples web service para auxiliar no agendamento de consultas médicas. Podendo gerenciar pacientes, doutores e consultas médicas.

## Uso
Pré-requisitos:
- Java 17

Abra seu terminal e insira os seguintes comandos:

```bash
# Para clonar o projeto
git clone github.com/AndCandido/Medical-Appointment-Schedule.git

#Para executar o projeto
./mvnw spring-boot:run
```

Assim o servidor estará disponível na porta ```8008```

## Recursos

### Patient

GET ```/patients``` Lista todos os pacientes.<br/>
GET ```/patients/{id: UUID}``` Retorna o paciente com o Id passado na rota.<br/>
POST ```/patients``` Salva o paciente passado no corpo da requisição.<br/>
PUT ```/patients/{id: UUID}``` Atualiza o paciente com o Id passado na rota.<br/>
DELETE ```/patients/{id: UUID}``` Deleta o paciente com o Id passado na rota.<br/>

```
name: string
email: string
contactPhone: string    
ageGroup: AgeGroup { BABY, CHILD, TEENAGER, ADULT, ELDERLY }
```

### Doctor

GET ```/doctors``` Lista todos os doutores.<br/>
GET ```/doctors/{id: UUID}``` Retorna o doutor com o Id passado na rota.<br/>
POST ```/doctors``` Salva o doutor passado no corpo da requisição.<br/>
PUT ```/doctors/{id: UUID}``` Atualiza o doutor com o Id passado na rota.<br/>
DELETE ```/doctors/{id: UUID}``` Deleta o doutor com o Id passado na rota.<br/>

```
name: string
nickname: string
phone: string
```

### Appointment

GET ```/appointments``` Lista todas as consultas.<br/>
GET ```/appointments/{id: UUID}``` Retorna a consulta com o Id passado na rota.<br/>
POST ```/appointments``` Salva a consulta passada no corpo da requisição.<br/>
PUT ```/appointments/{id: UUID}``` Atualiza a consulta com o Id passado na rota.<br/>
DELETE ```/appointments/{id: UUID}``` Deleta a consulta com o Id passado na rota.<br/>

```
appointmentDate: Date LocalDate
appointmentTime: Time LocalTime
doctorId: string UUID
patientId: string UUID
```

