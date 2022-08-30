package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

/**
 * Created by jt on 2019-04-23.
 */
@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = false)
@Component
public class BreweryClient {

    public final String BEER_PATH_V1 = "/api/v1/beer/";
    private final String CUSTOMER_ENDPOINT = "/api/v1/customer/";
    private String apihost;

    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeerById(UUID uuid){
        return restTemplate.getForObject(apihost + BEER_PATH_V1 + uuid.toString(), BeerDto.class);
    }

    public URI saveNewBeer(BeerDto beerDto){
        return restTemplate.postForLocation(apihost + BEER_PATH_V1, beerDto);
    }

    public void updateBeer(UUID uuid, BeerDto beerDto){
        restTemplate.put(apihost + BEER_PATH_V1 + "/" + uuid.toString(), beerDto);
    }

    public void deleteBeer(UUID uuid){
        restTemplate.delete(apihost + BEER_PATH_V1 + "/" + uuid );
    }

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }


    public CustomerDto getCustomer(UUID id){
        return restTemplate.getForObject(apihost +CUSTOMER_ENDPOINT+ id.toString(),CustomerDto.class);
    }

    public URI saveCustomer(CustomerDto customerDto){
        return restTemplate.postForLocation(apihost+CUSTOMER_ENDPOINT+customerDto.getId().toString(),customerDto);
    }

    public void updateCustomer(UUID uuid ,CustomerDto customerDto){
        restTemplate.put(apihost+CUSTOMER_ENDPOINT+ uuid.toString(),customerDto);
    }

    public void deleteCustomerDto(UUID id){
        restTemplate.delete(apihost+CUSTOMER_ENDPOINT+id.toString(),id);
    }
}
