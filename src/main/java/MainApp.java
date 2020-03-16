import com.tdf.Config;
import com.tdf.Device;
import com.tdf.DeviceDAO;
import com.tdf.MyController;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class MainApp extends Application<Config> {

    public static void main(String[] args) throws Exception {
        System.out.println("Running");
        new MainApp().run(args);
    }

    public void run(Config config, Environment environment) throws Exception {

        final DeviceDAO deviceDAO = new DeviceDAO(hibernate.getSessionFactory());
        final MyController resource = new MyController(deviceDAO);
        environment.
                jersey().register(resource);

        System.out.println("conf "+config.getPerson());
    }


    private HibernateBundle<Config> hibernate = new HibernateBundle<Config>(Device.class) {
        public DataSourceFactory getDataSourceFactory(Config configuration) {
            final DataSourceFactory database = configuration.getDatabase();
            return database;
        }
    };

    @Override
    public void initialize(Bootstrap<Config> bootstrap) {
        bootstrap.addBundle(hibernate);
    }
}
