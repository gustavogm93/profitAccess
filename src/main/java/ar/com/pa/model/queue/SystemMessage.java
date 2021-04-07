package ar.com.pa.model.queue;

import java.io.Serializable;

import ar.com.pa.model.props.Country;
import ar.com.pa.model.props.Region;
import lombok.Data;

@Data
public class SystemMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private Country country;
    private Region region;
    
	public SystemMessage(Country country, Region region) {
		this.country = country;
		this.region = region;
	}
    

}