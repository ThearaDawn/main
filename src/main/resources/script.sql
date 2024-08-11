select * from student st
                  inner join student_id_card stic
                             on st.id = stic.student_id;

select * from student_id_card stic;