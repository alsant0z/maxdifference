# maxdifference

You are given an array of integers and must compute the maximum difference between any item and any lower indexed smaller item for all the possible pairs, i.e., for a given array a find the maximum value of a[j] - a[i] for all i, j where 0 ≤ i < j < n and a[i] < a[j]. If there are no lower indexed smaller items for all the items, then return -1.
 
For example, given an array [ 1, 2, 6, 4], you would first compare 2 to the elements to its left.  1 is smaller, so calculate the difference 2 - 1 = 1.  6 is bigger than 2 and 1, so calculate the differences 4 and 5.  4 is only bigger than 2 and 1, and the differences are 2 and 3.  The largest difference was 6 - 1 = 5.

Olá Alexandre e André,

Conforme conversamos segue as tarefas de 1 a 5 anexadas a GMUD de produção para deploy da solução BON (BRN) Gávea. Gostaria de solicitar revisão e ajuste das tarefas 3, 4 e 5 conforme conversamos em reunião (caso queira mais detalhes à respeito dos ajuste peço informar).

Quanto aos artefatos Middleware e CordApp da B3 peço pfv que vcs realizem, através da sua VDI, a coleta dos artefatos que irei enviar para o e-mail B3 e façam o commit no Bitbucket (https://bitbucket.intraservice.corp/projects/BNOC) na pasta “File” e que seja feito ajuste no script de deploy para coletar este artefato do bitbucket.

Outro ponto muito importante é a descrição da tarefa de validação que consiste em descrever como iremos realizar as validações das integrações e conectividades assim como a estrutura básica funcional (input com JSON no middleware e consulta ao “Node B3 Registro” para verificar os states de “proposta de emissão” e “registro finalizado”).

Quanto ao NASCORAPP peço que vcs realizem uma análise para identificar, dado que vamos utilizar o mesmo script do BNO Compacto, se não existe nenhum tipo de problema/conflito com as chaves e identidades que já foram criadas nos diretórios do projeto BNO Compacto. Não seria prudente realizar um clean no diretório //nascorapp/Shares/Aplicativos/BNO

Na tarefa 5 colocar em detalhes a verificação se não existe resquício do deploy do projeto “BNO Compacto” antes de setar a variável "tmp_tag=tmp01". Caso existe resquício do outro deploy detalhar o procedimento a ser adotado como por exemplo configurar a variável “tmp_tag” com valor acima do registrado na máquina ou executar o clean do tmp.

Qualquer dúvida estou à disposição.

Obrigado !

Tarefas da GMUD:

•	Tarefa 1 - ##DBA - Criação de Tabelas ##
o	Descrição
TECNOLOGIA - AUTOMACAO E IMPLANTACAO - BD || ## DBA implementar no HOST = oraeng001p-scan.intraservice.corp SERVICE_NAME = OTC os scripts abaixo conforme schema: 
create table PR01OTCNSSO.notary_committed_states ( state_ref varchar(73) not null, consuming_transaction_id varchar(64) not null, constraint id1 primary key (state_ref) ); 
create table PR01OTCNSSO.notary_committed_transactions ( transaction_id varchar(64) not null, constraint id2 primary key (transaction_id) ); 
create table PR01OTCNSSO.notary_request_log ( id varchar(76) not null, consuming_transaction_id varchar(64), requesting_party_name varchar(255), request_timestamp timestamp not null, request_signature RAW(1024) not null, worker_node_x500_name varchar(255), constraint id3 primary key (id) ); 
create table PR01OTCNSSO.notary_double_spends ( state_ref varchar(73) not null, request_timestamp timestamp not null, consuming_transaction_id varchar(64) not null, constraint id4 primary key (state_ref) ); 
create index tx_idx on PR01OTCNSSO.notary_request_log(consuming_transaction_id); 
create index state_ts_tx_idx on PR01OTCNSSO.notary_double_spends (state_ref,request_timestamp,consuming_transaction_id); 
GRANT SELECT, INSERT ON PR01OTCNSSO.notary_double_spends TO PR01OTCNSSU; 
GRANT SELECT, INSERT ON PR01OTCNSSO.notary_committed_states TO PR01OTCNSSU; 
GRANT SELECT, INSERT ON PR01OTCNSSO.notary_committed_transactions TO PR01OTCNSSU; 
GRANT SELECT, INSERT ON PR01OTCNSSO.notary_request_log TO PR01OTCNSSU;

•	Tarefa 2 - ## DBA - Repassar credencias do owner ##
o	Descrição
TECNOLOGIA - AUTOMACAO E IMPLANTACAO - BD || DBA - Repassar para o analista da “OPs TI Clearing Depositaria e Balcao” as senhas da seguintes contas para que o ansible possa realizar o processo de instalação dos componentes sistêmicos: 
Owner: 
PR01CENMIDO 
PR01CENMNMO 
HOST = oraeng001p-scan.intraservice.corp SERVICE_NAME = CENM
Owner: 
PR01OTCNWAO 
PR01OTCNWBO
PR01OTCNWCO
PR01OTCNSSO
PR01OTCNB3O
HOST = oraeng001p-scan.intraservice.corp SERVICE_NAME = OTC
Owner:
PR01GAVEAO
HOST = oraeng001p-scan.intraservice.corp SERVICE_NAME = GAVEA

•	Tarefa 3 - ## OPs TI Clearing Depositaria e Balcao - Copia de playbook ##
OPs TI Clearing Depositaria e Balcao || 
A partir do servidor APSPOS56L1P 
Efetuar publicação da pasta anexa com as playbooks para instalação do sistema: 
1-publicar em: /etc/ansible/bno-corda 
2-Fazer o export da variável no linux: export ANSIBLE_CONFIG=/etc/ansible/bno-corda/ambient/production/configs/ansible.cfg
•	Tarefa 4 ## OPs TI Clearing Depositaria e Balcao - Alterar variaveis na plabook ##
Obter a conta e senha do owner para: 
PR01OTCNB3O
PR01OTCNSSO
PR01OTCNWAO
PR01OTCNWBO
PR01OTCNWCO
HOST = oraeng001p-scan.intraservice.corp SERVICE_NAME = OTC 
e alterar em: 
/etc/ansible/bno-corda/inventories/production/hosts.yaml
/etc/ansible/bno-corda/inventories/prod/group_vars/notary.yml
/etc/ansible/bno-corda/inventories/prod/group_vars/cenm.yml 

obter: -conta de serviço de produção -credenciais de acesso ao nascor -servidor de banco: (hostname e service), alterando em: /etc/ansible/bno-corda/inventories/prod/group_vars/all.yml
obter: -conta de serviço de produção -credenciais de acesso ao nascor do middleware, que faz o mountpoint das pastas de input e output dos arquivos cpr, alterando em /etc/ansible/bno-corda/inventories/prod/group_vars/middleware.yml 

•	Tarefa 5 - ## OPs TI Clearing Depositaria e Balcao - Execução da playbook ###

Descrição
Apos apontados os dados de owner/senha dos schema; validado o usuário de serviço; usuário do nascor: executar, na sequência abaixo: 
em: /etc/ansible/bno-corda/ 
1.	ansible-playbook -i inventories/production/hosts.yml --extra-vars "tmp_tag=tmp01" provisioning.yaml
2.	ansible-playbook -i inventories/production/hosts.yml --extra-vars "tmp_tag=tmpXX" provisioning_cordapps.yml
3.	ansible-playbook -i inventories/production/hosts.yml --extra-vars "tmp_tag=tmpXX" provisioning_middleware.yml

