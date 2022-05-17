alter table formas_pagamentos add data_atualizacao datetime null;

update formas_pagamentos set data_atualizacao = utc_timestamp;

alter table formas_pagamentos modify data_atualizacao datetime not null;