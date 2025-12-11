# 📘 Sistema de Gerenciamento de Clientes, Pets e Serviços

Este projeto é um sistema completo desenvolvido em **Java (Swing)**, organizado em múltiplas interfaces GUI (total: 17 telas). Ele permite realizar o gerenciamento de **clientes**, **pets** e **serviços**, incluindo contratação, listagem, busca e cancelamento.

O objetivo é demonstrar domínio de **Programação Orientada a Objetos**, **interface gráfica com Swing** e **organização modular de sistemas**.

---

## 🎯 **Funcionalidades Principais**

### 🧍 Gestão de Clientes

* Cadastro de clientes
* Listagem de clientes
* Consulta de clientes por CPF
* Exclusão de clientes

### 🐾 Gestão de Pets

* Cadastro de pets vinculados ao dono
* Listagem geral de pets
* Consulta por CPF do dono
* Exclusão de pets

### 🛠 Gestão de Serviços

* Contratação de **serviços avulsos**
* Contratação de **pacotes de serviços** (com desconto)
* Listagem de serviços contratados
* Busca de serviços por CPF
* Cancelamento de serviços

---

## 🖥 **Interfaces Implementadas (GUI Swing)**

### 🏠 TelaPrincipal

Tela inicial do sistema, permitindo acessar:

* Gerenciar Clientes
* Gerenciar Pets
* Gerenciar Serviços

### 👥 Telas de Clientes

* **TelaClientes** (menu principal dos clientes)
* **TelaCadastroCliente**
* **TelaListaClientes**
* **TelaBuscarCliente**
* **TelaExcluirCliente**

### 🐶 Telas de Pets

* **TelaPets** (menu principal dos pets)
* **TelaCadastroPet**
* **TelaListaPets** (modelo central com ID do pet)
* **TelaBuscarPet**
* **TelaExcluirPet**

### 🛠 Telas de Serviços

* **TelaServicos** (menu principal)
* **InterfaceContratarServico** (serviço avulso)
* **InterfaceContratarPacote** (pacote com desconto)
* **InterfaceListarServicos**
* **InterfaceBuscarServicos**
* **InterfaceCancelarServico**

---

## 📂 **Arquitetura do Projeto**

O sistema segue uma estrutura organizada, com cada funcionalidade separada em telas independentes:

```
src/
 └── gui/
      ├── clientes/
      ├── pets/
      ├── servicos/
      └── TelaPrincipal.java
```

Essa separação facilita manutenção, testes e expansão do sistema.

---

## 🧩 **Destaques Técnicos**

* Interfaces gráficas desenvolvidas manualmente usando **Swing**
* Separação modular clara entre funcionalidades
* Navegação intuitiva entre as telas
* Uso de **JFrame**, **JPanel**, **JTable**, **ActionListener**, **GridLayout** e **Layouts absolutos**
* Validação básica de dados (CPF, campos obrigatórios etc.)
* Simulação de "banco" em listas internas

---

## ▶ Como executar

1. Importar o projeto em uma IDE (IntelliJ, NetBeans ou Eclipse)
2. Compilar o projeto
3. Executar a classe **TelaPrincipal.java**

---

## 📄 Licença

Este projeto é distribuído sob a licença MIT — livre para uso e modificação.

---
