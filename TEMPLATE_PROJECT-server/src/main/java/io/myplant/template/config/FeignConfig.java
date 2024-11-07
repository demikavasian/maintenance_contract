package io.myplant.template.config;

//import com.gejenbacher.myplant.maintenance.api.MaintenanceEventApi;
//import io.myplant.debriefreports.feign.debrief.DebriefApi;
//import io.myplant.debriefreports.feign.servicerequest.ServiceRequestApi;
import io.myplant.feign.security.MyPlantBlockingFeignAuthConfiguration;
//import io.myplant.seshat.api.AssetApi;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


// todo review which feign clients you need to import
@Configuration
@Import(MyPlantBlockingFeignAuthConfiguration.class)
@EnableFeignClients(basePackageClasses = {
//     MaintenanceEventApi.class,
//     AssetApi.class,
//     ServiceRequestApi.class,
//     DebriefApi.class
})
public class FeignConfig {
}
