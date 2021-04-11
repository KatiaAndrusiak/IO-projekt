create type employee_position as enum (
    'Dyrektor',
    'Menedżer',
    'Pracownik'
    );

create type employee_category as enum (
    '1',
    '2',
    'wyższa'
    );
	
create type holiday_name as enum (
    'Wielkanoc',
    'Boże Narodzenie'
    );
	
create type checkup_question as enum (
    'Czy wilgotność w pomieszczeniu jest zgodna z zasadami?',
    'Czy wszystkie lodówki są sprawne?',
    'Czy wszyscy pracownicy są wyposażeni w ŚOI?'
    );
	
create type checkup_answer as enum (
    'Tak',
    'Nie'
    );
	
create type facility_status as enum (
    'Apteka',
    'Biuro',
    'Magazyn'
    );