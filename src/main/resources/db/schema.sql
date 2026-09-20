create database banco_contas_bancarias;

create table correntista(

                            id BIGINT primary key auto_increment,
                            nome VARCHAR(255),
                            documento VARCHAR(15),
                            contato VARCHAR(255)

);

create table conta(

                      id BIGINT primary key auto_increment,
                      numero VARCHAR(255),
                      saldo DECIMAL(10,2) default 0.00,
                      tipo ENUM('CONTA_POUPANCA', 'CONTA_CORRENTE'),
                      id_correntista BIGINT,
                      constraint fk_correntista foreign key (id_correntista) references correntista(id)

);

create table transacao(

                          id BIGINT primary key auto_increment,
                          tipo ENUM('DEPOSITO', 'SAQUE', 'RENDIMENTO') not null,
                          valor DECIMAL(10,2) not null,
                          data DATETIME not null,
                          id_conta_remetente BIGINT,
                          id_conta_destinatario BIGINT,
                          constraint fk_remetente foreign key (id_conta_remetente) references conta(id),
                          constraint fk_destinatario foreign key (id_conta_destinatario) references conta(id)

);