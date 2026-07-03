INSERT INTO member_type (id, name) VALUES
    ('8FE4113D4E4020E0DCF887803A886981', 'Active Member'),
    ('4237C55C5CC3B4B082CBF2540612778E', 'Social Member');

INSERT INTO membership_form (club_id, form_id, title, description, registration_opens) VALUES
    (
        'britsport',
        'B171388180BC457D9887AD92B6CCFC86',
        'Coding camp summer 2025',
        'Coding camp summer 2025 signup form.',
        '2024-12-16T00:00:00Z'
    ),
    (
        'britsport',
        'F171388180BC457D9887AD92B6CCFC87',
        'Coding camp summer 2027',
        'Future coding camp signup form.',
        '2027-12-16T00:00:00Z'
    );

INSERT INTO membership_form_member_type (membership_form_id, member_type_id)
SELECT mf.id, mt.id
FROM membership_form mf
         JOIN member_type mt ON mt.id IN (
             '8FE4113D4E4020E0DCF887803A886981',
             '4237C55C5CC3B4B082CBF2540612778E'
         )
WHERE mf.form_id IN (
    'B171388180BC457D9887AD92B6CCFC86',
    'F171388180BC457D9887AD92B6CCFC87'
);
