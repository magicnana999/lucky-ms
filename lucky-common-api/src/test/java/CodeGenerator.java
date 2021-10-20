import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.IFileCreate;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * @author magicnana
 * @date 2021/8/23 11:23
 */
public class CodeGenerator {

  public static void main(String[] args) {
    CodeGenerator codeGenerator = new CodeGenerator();
    codeGenerator.createEntity("user");
  }

  private static InjectionConfig getInjectionConfig() {
    InjectionConfig ic =
        new InjectionConfig() {

          @Override
          public void initMap() {}

          @Override
          public IFileCreate getFileCreate() {
            return (configBuilder, fileType, filePath) -> {
              switch (fileType) {
                case ENTITY:
                  return true;
                case MAPPER:
                  return false;
                case XML:
                  return false;
                case SERVICE:
                  return false;
                case SERVICE_IMPL:
                  return false;
                case CONTROLLER:
                  return false;
                case OTHER:
                  return false;
                default:
                  return false;
              }
            };
          }
        };
    return ic;
  }


  public void generateEntity() {
    createEntity(
        "t_device",
        "t_log",
        "t_permission",
        "t_product",
        "t_resource",
        "t_resource_action",
        "t_user",
        "t_manager",
        "t_role",
        "t_user_device",
        "t_user_role",
        "t_role_permission");
  }

  public void createEntity(String... tablename) {
    AutoGenerator ag = init();
//    ag.getStrategy().setTablePrefix("t_");
    ag.getStrategy().setInclude(tablename);
    ag.execute();
  }

  public AutoGenerator init() {
    // 1、创建代码生成器
    AutoGenerator mpg = new AutoGenerator();

    // 2、全局配置
    GlobalConfig gc = new GlobalConfig();
    String projectPath = System.getProperty("user.dir");
    gc.setOutputDir(projectPath + "/lucky-common-api/src/main/java");
    gc.setAuthor("MyBatis");
    gc.setOpen(false); // 生成后是否打开资源管理器
    gc.setFileOverride(false); // 重新生成时文件是否覆盖
    gc.setIdType(IdType.ASSIGN_ID); // 主键策略
    gc.setDateType(DateType.ONLY_DATE); // 定义生成的实体类中日期类型
    gc.setSwagger2(true); // 开启Swagger2模式
    mpg.setGlobalConfig(gc);

    // 3、数据源配置
    DataSourceConfig dsc = new DataSourceConfig();
    dsc.setUrl("jdbc:mysql://192.144.232.45:3306/new_luckgt?verifyServerCertificate=false&useSSL=false&requireSSL=false");
    dsc.setDriverName("com.mysql.cj.jdbc.Driver");
    //    dsc.setDriverName("com.mysql.jdbc.Driver");
    dsc.setUsername("new_luckgt");
    dsc.setPassword("pxixYRJ3RN7BmhTz");
    dsc.setDbType(DbType.MYSQL);
    mpg.setDataSource(dsc);

    // 4、包配置
    PackageConfig pc = new PackageConfig();
    pc.setModuleName(null); // 模块名
    pc.setParent("com.jc.lucky.common");
    pc.setEntity("entity");
    pc.setMapper("mapper");

    mpg.setPackageInfo(pc);

    // 5、策略配置
    StrategyConfig strategy = new StrategyConfig();
    strategy.setNaming(NamingStrategy.underline_to_camel); // 数据库表映射到实体的命名策略
//    strategy.setTablePrefix("d_"); // 生成实体时去掉表前缀

    strategy.setColumnNaming(NamingStrategy.underline_to_camel); // 数据库表字段映射到实体的命名策略
    strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作
    //    strategy.setSuperControllerClass(AbstractController.class);
    //    strategy.setRestControllerStyle(true); // restful api风格控制器
    //    strategy.setControllerMappingHyphenStyle(true); // url中驼峰转连字符

    mpg.setStrategy(strategy);

    mpg.setTemplateEngine(new FreemarkerTemplateEngine());
    // 配置模板
    TemplateConfig templateConfig = new TemplateConfig();
    // 不生成mapper.xml
    templateConfig.disable(TemplateType.CONTROLLER, TemplateType.SERVICE);

    mpg.setCfg(getInjectionConfig());

    // 6、执行
    //    mpg.execute();
    return mpg;
  }
}
