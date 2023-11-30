package org.example;

@RestController
public class GeodataController {

    private final GeodataRepository geodataRepository;
    private final PersonService personService;
    private final LocationService locationService;
    private final WeatherService weatherService;

    public GeodataController(GeodataRepository geodataRepository, PersonService personService, LocationService locationService, WeatherService weatherService) {
        this.geodataRepository = geodataRepository;
        this.personService = personService;
        this.locationService = locationService;
        this.weatherService = weatherService;
    }

    @GetMapping("/geodata/{location}")
    public Geodata getGeodata(@PathVariable String location) {
        Location foundLocation = locationService.findLocationByName(location);
        Weather weather = weatherService.getWeatherByLocation(foundLocation);
        Person person = personService.findPersonByLocation(foundLocation);

        return new Geodata(foundLocation, person, weather);
    }
}