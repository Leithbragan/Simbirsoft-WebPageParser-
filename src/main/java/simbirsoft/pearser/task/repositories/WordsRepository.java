package simbirsoft.pearser.task.repositories;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import simbirsoft.pearser.task.models.WordData;
import java.util.HashMap;

public class WordsRepository {

    private Session session;

    private Session createHibernateSession() {

        SessionFactory sessionFactory;

        try {
            Configuration conf = new Configuration();
            conf.configure("hibernate.cfg.xml");

            sessionFactory = conf.buildSessionFactory();
            session = sessionFactory.openSession();

        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
        return session;
    }

    private void recordsAdd(HashMap<String, Integer> words, String url) {

        try {

            Transaction tx = session.beginTransaction();
            for (String word : words.keySet()) {
                WordData wordData = new WordData();
                wordData.setWord(word);
                wordData.setCount(words.get(word));
                wordData.setUrl(url);
                session.save(wordData);
            }
            tx.commit();

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }
    }

    public WordsRepository(HashMap<String, Integer> words, String url) {

        session = createHibernateSession();

        if (session != null) {
            recordsAdd(words, url);

            if (session.isOpen())
                session.close();
        }
    }

}
