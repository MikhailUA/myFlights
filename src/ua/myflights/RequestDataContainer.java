package ua.myflights;	

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RequestDataContainer { // обработка json
	
	private JSONObject jsonObj;
	private JSONArray Legs;
	private JSONObject Leg;
	
	private JSONArray Itineraries;
	private JSONObject Itinerary;
	
	private JSONArray Places; 
	private JSONObject Place;
	
	private ArrayList<Flight> Flights;
	
	public RequestDataContainer(String json) throws ParseException{
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(json);
		jsonObj = (JSONObject) obj;	
		Legs = (JSONArray) jsonObj.get("Legs");
		Itineraries = (JSONArray) jsonObj.get("Itineraries");
		Places = (JSONArray) jsonObj.get("Places");
		
		setFlightInfo(Legs, Itineraries);
		//setFlightInfoFromItineraries("Itineraries");
	}
	
	private void setFlightInfo(JSONArray Legs, JSONArray Itineraries){
		Flights = new ArrayList<Flight>();
		for (int i = 0; i<Legs.size();i++){
			Flight f = new Flight();
			
			Leg = (JSONObject) Legs.get(i);
			f.setDistId((String) Leg.get("Id"));
			f.setOriginStationId(Leg.get("OriginStation").toString());
			f.setDestinationStationId(Leg.get("DestinationStation").toString());
			
			f.setOriginStationName(getPlaceTitle(Leg.get("OriginStation").toString()));
			f.setDestinationStationName(getPlaceTitle(Leg.get("DestinationStation").toString()));

			f.setOriginStationCode(getPlaceCode(Leg.get("OriginStation").toString()));
			f.setDestinationStationCode(getPlaceCode(Leg.get("DestinationStation").toString()));
			
			f.setDuration((long) Leg.get("Duration"));	
			f.setArrivalTime((String) Leg.get("Arrival"));
			f.setDepartureTime((String) Leg.get("Departure"));
			
			Itinerary = (JSONObject) Itineraries.get(i);
			JSONArray PricingOptions = (JSONArray) Itinerary.get("PricingOptions");
			JSONObject PricingOption = (JSONObject) PricingOptions.get(0);
			f.setPrice((double) PricingOption.get("Price"));
			//System.out.println(getPlaceTitle(Leg.get("OriginStation").toString()));
			Flights.add(f);
		}
	}
	
	
	private String getPlaceTitle(String id){
		String Name = null;
		
		for (int i = 0; i < Places.size(); i++){
			Place = (JSONObject) Places.get(i);
			if (Place.get("Id").toString().equals(id)){
				Name = (String) Place.get("Name");
				break;
			}
		}
		return Name;		
	}
	
	private String getPlaceCode(String id){
		String Code = null;
		
		for (int i = 0; i < Places.size(); i++){
			Place = (JSONObject) Places.get(i);
			if (Place.get("Id").toString().equals(id)){
				Code = (String) Place.get("Code");
				break;
			}
		}
		return Code+"-sky";		
	}
	
	
	public ArrayList<Flight> getFlights(){
		return this.Flights;
	}

	
	/*
	public static void main(String[] args) throws ParseException {
		
		String json = "{\"Status\":\"UpdatesComplete\",\"Carriers\":[{\"ImageUrl\":\"http:\\/\\/s1.apideeplink.com\\/images\\/airlines\\/PS.png\",\"Id\":1571,\"Code\":\"PS\",\"Name\":\"Ukraine International\",\"DisplayCode\":\"PS\"},{\"ImageUrl\":\"http:\\/\\/s1.apideeplink.com\\/images\\/airlines\\/B2.png\",\"Id\":866,\"Code\":\"B2\",\"Name\":\"Belavia\",\"DisplayCode\":\"B2\"}],\"Legs\":[{\"SegmentIds\":[0],\"Duration\":60,\"Arrival\":\"2016-12-22T10:25:00\",\"Carriers\":[1571],\"Directionality\":\"Outbound\",\"OriginStation\":12911,\"Departure\":\"2016-12-22T09:25:00\",\"FlightNumbers\":[{\"CarrierId\":1571,\"FlightNumber\":\"25\"}],\"JourneyMode\":\"Flight\",\"DestinationStation\":12288,\"Stops\":[],\"OperatingCarriers\":[1571],\"Id\":\"12911-1612220925--31728-0-12288-1612221025\"},{\"SegmentIds\":[1],\"Duration\":60,\"Arrival\":\"2016-12-22T20:40:00\",\"Carriers\":[1571],\"Directionality\":\"Outbound\",\"OriginStation\":12911,\"Departure\":\"2016-12-22T19:40:00\",\"FlightNumbers\":[{\"CarrierId\":1571,\"FlightNumber\":\"23\"}],\"JourneyMode\":\"Flight\",\"DestinationStation\":12288,\"Stops\":[],\"OperatingCarriers\":[1571],\"Id\":\"12911-1612221940--31728-0-12288-1612222040\"},{\"SegmentIds\":[2,3],\"Duration\":310,\"Arrival\":\"2016-12-22T23:15:00\",\"Carriers\":[866],\"Directionality\":\"Outbound\",\"OriginStation\":12428,\"Departure\":\"2016-12-22T18:05:00\",\"FlightNumbers\":[{\"CarrierId\":866,\"FlightNumber\":\"830\"},{\"CarrierId\":866,\"FlightNumber\":\"835\"}],\"JourneyMode\":\"Flight\",\"DestinationStation\":12288,\"Stops\":[14347],\"OperatingCarriers\":[866],\"Id\":\"12428-1612221805--32511-1-12288-1612222315\"},{\"SegmentIds\":[4,3],\"Duration\":250,\"Arrival\":\"2016-12-22T23:15:00\",\"Carriers\":[866],\"Directionality\":\"Outbound\",\"OriginStation\":12911,\"Departure\":\"2016-12-22T19:05:00\",\"FlightNumbers\":[{\"CarrierId\":866,\"FlightNumber\":\"835\"},{\"CarrierId\":866,\"FlightNumber\":\"846\"}],\"JourneyMode\":\"Flight\",\"DestinationStation\":12288,\"Stops\":[14347],\"OperatingCarriers\":[866],\"Id\":\"12911-1612221905--32511-1-12288-1612222315\"}],\"Itineraries\":[{\"BookingDetailsLink\":{\"Method\":\"PUT\",\"Uri\":\"\\/apiservices\\/pricing\\/v1.0\\/7b0f088cef1d4550be91c94d98d0c8d2_ecilpojl_731B263A642C1CEECB5F7F0CFCBB5ACF\\/booking\",\"Body\":\"OutboundLegId=12911-1612220925--31728-0-12288-1612221025&InboundLegId=\"},\"OutboundLegId\":\"12911-1612220925--31728-0-12288-1612221025\",\"PricingOptions\":[{\"DeeplinkUrl\":\"http:\\/\\/partners.api.skyscanner.net\\/apiservices\\/deeplink\\/v2?_cje=jzj5DawL5zJyT%2bnfeP9GJWfImnVvZd7vh0AJSObmdOp8YP07VbGmhzc%2bVTc80nUp&url=http%3a%2f%2fwww.apideeplink.com%2ftransport_deeplink%2f4.0%2fUA%2fen-gb%2fUAH%2fukra%2f1%2f12911.12288.2016-12-22%2fair%2fairli%2fflights%3fitinerary%3dflight%7c-31728%7c25%7c12911%7c2016-12-22T09%3a25%7c12288%7c2016-12-22T10%3a25%26carriers%3d-31728%26passengers%3d1%26channel%3ddataapi%26cabin_class%3deconomy%26facilitated%3dfalse%26ticket_price%3d822.00%26is_npt%3dfalse%26is_multipart%3dfalse%26client_id%3dskyscanner_b2b%26request_id%3d39bbef79-be6f-464a-9eef-bf8fe1582015%26commercial_filters%3dfalse%26q_datetime_utc%3d2016-12-05T13%3a43%3a30\",\"Price\":822.0,\"Agents\":[4155761],\"QuoteAgeInMinutes\":52},{\"DeeplinkUrl\":\"http:\\/\\/partners.api.skyscanner.net\\/apiservices\\/deeplink\\/v2?_cje=jzj5DawL5zJyT%2bnfeP9GJWfImnVvZd7vh0AJSObmdOp8YP07VbGmhzc%2bVTc80nUp&url=http%3a%2f%2fwww.apideeplink.com%2ftransport_deeplink%2f4.0%2fUA%2fen-gb%2fUAH%2faebl%2f1%2f12911.12288.2016-12-22%2fair%2ftrava%2fflights%3fitinerary%3dflight%7c-31728%7c25%7c12911%7c2016-12-22T09%3a25%7c12288%7c2016-12-22T10%3a25%26carriers%3d-31728%26passengers%3d1%26channel%3ddataapi%26cabin_class%3deconomy%26facilitated%3dfalse%26ticket_price%3d847.50%26is_npt%3dfalse%26is_multipart%3dfalse%26client_id%3dskyscanner_b2b%26request_id%3d39bbef79-be6f-464a-9eef-bf8fe1582015%26deeplink_ids%3deu-central-1.prod_8db7d0bb986de58ba84ad74d6de738c6%26commercial_filters%3dfalse%26q_datetime_utc%3d2016-12-05T13%3a43%3a29\",\"Price\":847.5,\"Agents\":[1929340],\"QuoteAgeInMinutes\":52},{\"DeeplinkUrl\":\"http:\\/\\/partners.api.skyscanner.net\\/apiservices\\/deeplink\\/v2?_cje=jzj5DawL5zJyT%2bnfeP9GJWfImnVvZd7vh0AJSObmdOp8YP07VbGmhzc%2bVTc80nUp&url=http%3a%2f%2fwww.apideeplink.com%2ftransport_deeplink%2f4.0%2fUA%2fen-gb%2fUAH%2fotua%2f1%2f12911.12288.2016-12-22%2fair%2ftrava%2fflights%3fitinerary%3dflight%7c-31728%7c25%7c12911%7c2016-12-22T09%3a25%7c12288%7c2016-12-22T10%3a25%26carriers%3d-31728%26passengers%3d1%26channel%3ddataapi%26cabin_class%3deconomy%26facilitated%3dfalse%26ticket_price%3d911.58%26is_npt%3dfalse%26is_multipart%3dfalse%26client_id%3dskyscanner_b2b%26request_id%3d39bbef79-be6f-464a-9eef-bf8fe1582015%26deeplink_ids%3deu-central-1.prod_6e362af72f55d43a828ff148c479d7c1%26commercial_filters%3dfalse%26q_datetime_utc%3d2016-12-05T13%3a43%3a33\",\"Price\":911.58,\"Agents\":[3513089],\"QuoteAgeInMinutes\":52},{\"DeeplinkUrl\":\"http:\\/\\/partners.api.skyscanner.net\\/apiservices\\/deeplink\\/v2?_cje=jzj5DawL5zJyT%2bnfeP9GJWfImnVvZd7vh0AJSObmdOp8YP07VbGmhzc%2bVTc80nUp&url=http%3a%2f%2fwww.apideeplink.com%2ftransport_deeplink%2f4.0%2fUA%2fen-gb%2fUAH%2ftkua%2f1%2f12911.12288.2016-12-22%2fair%2ftrava%2fflights%3fitinerary%3dflight%7c-31728%7c25%7c12911%7c2016-12-22T09%3a25%7c12288%7c2016-12-22T10%3a25%26carriers%3d-31728%26passengers%3d1%26channel%3ddataapi%26cabin_class%3deconomy%26facilitated%3dfalse%26ticket_price%3d945.00%26is_npt%3dfalse%26is_multipart%3dfalse%26client_id%3dskyscanner_b2b%26request_id%3d39bbef79-be6f-464a-9eef-bf8fe1582015%26deeplink_ids%3deu-central-1.prod_7ebe8ea925fcd6961d45d3208caccda9%26commercial_filters%3dfalse%26q_datetime_utc%3d2016-12-05T13%3a43%3a34\",\"Price\":945.0,\"Agents\":[4045313],\"QuoteAgeInMinutes\":52},{\"DeeplinkUrl\":\"http:\\/\\/partners.api.skyscanner.net\\/apiservices\\/deeplink\\/v2?_cje=jzj5DawL5zJyT%2bnfeP9GJWfImnVvZd7vh0AJSObmdOp8YP07VbGmhzc%2bVTc80nUp&url=http%3a%2f%2fwww.apideeplink.com%2ftransport_deeplink%2f4.0%2fUA%2fen-gb%2fUAH%2fbfua%2f1%2f12911.12288.2016-12-22%2fair%2ftrava%2fflights%3fitinerary%3dflight%7c-31728%7c25%7c12911%7c2016-12-22T09%3a25%7c12288%7c2016-12-22T10%3a25%26carriers%3d-31728%26passengers%3d1%26channel%3ddataapi%26cabin_class%3deconomy%26facilitated%3dfalse%26ticket_price%3d1862.81%26is_npt%3dfalse%26is_multipart%3dfalse%26client_id%3dskyscanner_b2b%26request_id%3d39bbef79-be6f-464a-9eef-bf8fe1582015%26deeplink_ids%3deu-central-1.prod_f3d27c04bf1867960860304e39bf9eec%26commercial_filters%3dfalse%26q_datetime_utc%3d2016-12-05T13%3a43%3a29\",\"Price\":1862.81,\"Agents\":[2043137],\"QuoteAgeInMinutes\":52}]},{\"BookingDetailsLink\":{\"Method\":\"PUT\",\"Uri\":\"\\/apiservices\\/pricing\\/v1.0\\/7b0f088cef1d4550be91c94d98d0c8d2_ecilpojl_731B263A642C1CEECB5F7F0CFCBB5ACF\\/booking\",\"Body\":\"OutboundLegId=12911-1612221940--31728-0-12288-1612222040&InboundLegId=\"},\"OutboundLegId\":\"12911-1612221940--31728-0-12288-1612222040\",\"PricingOptions\":[{\"DeeplinkUrl\":\"http:\\/\\/partners.api.skyscanner.net\\/apiservices\\/deeplink\\/v2?_cje=jzj5DawL5zJyT%2bnfeP9GJWfImnVvZd7vh0AJSObmdOp8YP07VbGmhzc%2bVTc80nUp&url=http%3a%2f%2fwww.apideeplink.com%2ftransport_deeplink%2f4.0%2fUA%2fen-gb%2fUAH%2faebl%2f1%2f12911.12288.2016-12-22%2fair%2ftrava%2fflights%3fitinerary%3dflight%7c-31728%7c23%7c12911%7c2016-12-22T19%3a40%7c12288%7c2016-12-22T20%3a40%26carriers%3d-31728%26passengers%3d1%26channel%3ddataapi%26cabin_class%3deconomy%26facilitated%3dfalse%26ticket_price%3d1613.76%26is_npt%3dfalse%26is_multipart%3dfalse%26client_id%3dskyscanner_b2b%26request_id%3d39bbef79-be6f-464a-9eef-bf8fe1582015%26deeplink_ids%3deu-central-1.prod_84c487e08b711cd5c26fffb78206661f%26commercial_filters%3dfalse%26q_datetime_utc%3d2016-12-05T13%3a43%3a29\",\"Price\":1613.76,\"Agents\":[1929340],\"QuoteAgeInMinutes\":52},{\"DeeplinkUrl\":\"http:\\/\\/partners.api.skyscanner.net\\/apiservices\\/deeplink\\/v2?_cje=jzj5DawL5zJyT%2bnfeP9GJWfImnVvZd7vh0AJSObmdOp8YP07VbGmhzc%2bVTc80nUp&url=http%3a%2f%2fwww.apideeplink.com%2ftransport_deeplink%2f4.0%2fUA%2fen-gb%2fUAH%2fukra%2f1%2f12911.12288.2016-12-22%2fair%2fairli%2fflights%3fitinerary%3dflight%7c-31728%7c23%7c12911%7c2016-12-22T19%3a40%7c12288%7c2016-12-22T20%3a40%26carriers%3d-31728%26passengers%3d1%26channel%3ddataapi%26cabin_class%3deconomy%26facilitated%3dfalse%26ticket_price%3d1647.00%26is_npt%3dfalse%26is_multipart%3dfalse%26client_id%3dskyscanner_b2b%26request_id%3d39bbef79-be6f-464a-9eef-bf8fe1582015%26commercial_filters%3dfalse%26q_datetime_utc%3d2016-12-05T13%3a43%3a30\",\"Price\":1647.0,\"Agents\":[4155761],\"QuoteAgeInMinutes\":52},{\"DeeplinkUrl\":\"http:\\/\\/partners.api.skyscanner.net\\/apiservices\\/deeplink\\/v2?_cje=jzj5DawL5zJyT%2bnfeP9GJWfImnVvZd7vh0AJSObmdOp8YP07VbGmhzc%2bVTc80nUp&url=http%3a%2f%2fwww.apideeplink.com%2ftransport_deeplink%2f4.0%2fUA%2fen-gb%2fUAH%2fotua%2f1%2f12911.12288.2016-12-22%2fair%2ftrava%2fflights%3fitinerary%3dflight%7c-31728%7c23%7c12911%7c2016-12-22T19%3a40%7c12288%7c2016-12-22T20%3a40%26carriers%3d-31728%26passengers%3d1%26channel%3ddataapi%26cabin_class%3deconomy%26facilitated%3dfalse%26ticket_price%3d1721.63%26is_npt%3dfalse%26is_multipart%3dfalse%26client_id%3dskyscanner_b2b%26request_id%3d39bbef79-be6f-464a-9eef-bf8fe1582015%26deeplink_ids%3deu-central-1.prod_016e4f2a74cc7934c709f3fae65abc0e%26commercial_filters%3dfalse%26q_datetime_utc%3d2016-12-05T13%3a43%3a33\",\"Price\":1721.63,\"Agents\":[3513089],\"QuoteAgeInMinutes\":52},{\"DeeplinkUrl\":\"http:\\/\\/partners.api.skyscanner.net\\/apiservices\\/deeplink\\/v2?_cje=jzj5DawL5zJyT%2bnfeP9GJWfImnVvZd7vh0AJSObmdOp8YP07VbGmhzc%2bVTc80nUp&url=http%3a%2f%2fwww.apideeplink.com%2ftransport_deeplink%2f4.0%2fUA%2fen-gb%2fUAH%2ftkua%2f1%2f12911.12288.2016-12-22%2fair%2ftrava%2fflights%3fitinerary%3dflight%7c-31728%7c23%7c12911%7c2016-12-22T19%3a40%7c12288%7c2016-12-22T20%3a40%26carriers%3d-31728%26passengers%3d1%26channel%3ddataapi%26cabin_class%3deconomy%26facilitated%3dfalse%26ticket_price%3d1732.00%26is_npt%3dfalse%26is_multipart%3dfalse%26client_id%3dskyscanner_b2b%26request_id%3d39bbef79-be6f-464a-9eef-bf8fe1582015%26deeplink_ids%3deu-central-1.prod_3a22117ae9ab0b05ffd89a8447c03aab%26commercial_filters%3dfalse%26q_datetime_utc%3d2016-12-05T13%3a43%3a34\",\"Price\":1732.0,\"Agents\":[4045313],\"QuoteAgeInMinutes\":52},{\"DeeplinkUrl\":\"http:\\/\\/partners.api.skyscanner.net\\/apiservices\\/deeplink\\/v2?_cje=jzj5DawL5zJyT%2bnfeP9GJWfImnVvZd7vh0AJSObmdOp8YP07VbGmhzc%2bVTc80nUp&url=http%3a%2f%2fwww.apideeplink.com%2ftransport_deeplink%2f4.0%2fUA%2fen-gb%2fUAH%2fbfua%2f1%2f12911.12288.2016-12-22%2fair%2ftrava%2fflights%3fitinerary%3dflight%7c-31728%7c23%7c12911%7c2016-12-22T19%3a40%7c12288%7c2016-12-22T20%3a40%26carriers%3d-31728%26passengers%3d1%26channel%3ddataapi%26cabin_class%3deconomy%26facilitated%3dfalse%26ticket_price%3d2609.49%26is_npt%3dfalse%26is_multipart%3dfalse%26client_id%3dskyscanner_b2b%26request_id%3d39bbef79-be6f-464a-9eef-bf8fe1582015%26deeplink_ids%3deu-central-1.prod_1fcea4d760c35509c1368da41f66ee2f%26commercial_filters%3dfalse%26q_datetime_utc%3d2016-12-05T13%3a43%3a29\",\"Price\":2609.49,\"Agents\":[2043137],\"QuoteAgeInMinutes\":52}]},{\"BookingDetailsLink\":{\"Method\":\"PUT\",\"Uri\":\"\\/apiservices\\/pricing\\/v1.0\\/7b0f088cef1d4550be91c94d98d0c8d2_ecilpojl_731B263A642C1CEECB5F7F0CFCBB5ACF\\/booking\",\"Body\":\"OutboundLegId=12428-1612221805--32511-1-12288-1612222315&InboundLegId=\"},\"OutboundLegId\":\"12428-1612221805--32511-1-12288-1612222315\",\"PricingOptions\":[{\"DeeplinkUrl\":\"http:\\/\\/partners.api.skyscanner.net\\/apiservices\\/deeplink\\/v2?_cje=jzj5DawL5zJyT%2bnfeP9GJWfImnVvZd7vh0AJSObmdOp8YP07VbGmhzc%2bVTc80nUp&url=http%3a%2f%2fwww.apideeplink.com%2ftransport_deeplink%2f4.0%2fUA%2fen-gb%2fUAH%2fotua%2f1%2f12428.12288.2016-12-22%2fair%2ftrava%2fflights%3fitinerary%3dflight%7c-32511%7c830%7c12428%7c2016-12-22T18%3a05%7c14347%7c2016-12-22T20%3a00%3bflight%7c-32511%7c835%7c14347%7c2016-12-22T23%3a00%7c12288%7c2016-12-22T23%3a15%26carriers%3d-32511%26passengers%3d1%26channel%3ddataapi%26cabin_class%3deconomy%26facilitated%3dfalse%26ticket_price%3d4272.66%26is_npt%3dfalse%26is_multipart%3dfalse%26client_id%3dskyscanner_b2b%26request_id%3d39bbef79-be6f-464a-9eef-bf8fe1582015%26deeplink_ids%3deu-central-1.prod_0a3e98cd30a5c093ec5e5bc52ce0c3f4%26commercial_filters%3dfalse%26q_datetime_utc%3d2016-12-05T13%3a43%3a33\",\"Price\":4272.66,\"Agents\":[3513089],\"QuoteAgeInMinutes\":52}]},{\"BookingDetailsLink\":{\"Method\":\"PUT\",\"Uri\":\"\\/apiservices\\/pricing\\/v1.0\\/7b0f088cef1d4550be91c94d98d0c8d2_ecilpojl_731B263A642C1CEECB5F7F0CFCBB5ACF\\/booking\",\"Body\":\"OutboundLegId=12911-1612221905--32511-1-12288-1612222315&InboundLegId=\"},\"OutboundLegId\":\"12911-1612221905--32511-1-12288-1612222315\",\"PricingOptions\":[{\"DeeplinkUrl\":\"http:\\/\\/partners.api.skyscanner.net\\/apiservices\\/deeplink\\/v2?_cje=jzj5DawL5zJyT%2bnfeP9GJWfImnVvZd7vh0AJSObmdOp8YP07VbGmhzc%2bVTc80nUp&url=http%3a%2f%2fwww.apideeplink.com%2ftransport_deeplink%2f4.0%2fUA%2fen-gb%2fUAH%2fotua%2f1%2f12911.12288.2016-12-22%2fair%2ftrava%2fflights%3fitinerary%3dflight%7c-32511%7c846%7c12911%7c2016-12-22T19%3a05%7c14347%7c2016-12-22T21%3a05%3bflight%7c-32511%7c835%7c14347%7c2016-12-22T23%3a00%7c12288%7c2016-12-22T23%3a15%26carriers%3d-32511%26passengers%3d1%26channel%3ddataapi%26cabin_class%3deconomy%26facilitated%3dfalse%26ticket_price%3d4354.29%26is_npt%3dfalse%26is_multipart%3dfalse%26client_id%3dskyscanner_b2b%26request_id%3d39bbef79-be6f-464a-9eef-bf8fe1582015%26deeplink_ids%3deu-central-1.prod_c3cb4ce1646095eab07e27d9819b83b9%26commercial_filters%3dfalse%26q_datetime_utc%3d2016-12-05T13%3a43%3a33\",\"Price\":4354.29,\"Agents\":[3513089],\"QuoteAgeInMinutes\":52}]}],\"Query\":{\"Locale\":\"en-gb\",\"CabinClass\":\"Economy\",\"Currency\":\"UAH\",\"LocationSchema\":\"Default\",\"GroupPricing\":false,\"Country\":\"UA\",\"Adults\":1,\"Infants\":0,\"Children\":0,\"OutboundDate\":\"2016-12-22\",\"OriginPlace\":\"4110\",\"DestinationPlace\":\"12288\"},\"SessionKey\":\"7b0f088cef1d4550be91c94d98d0c8d2_ecilpojl_731B263A642C1CEECB5F7F0CFCBB5ACF\",\"Agents\":[{\"Status\":\"UpdatesComplete\",\"Type\":\"TravelAgent\",\"ImageUrl\":\"http:\\/\\/s1.apideeplink.com\\/images\\/websites\\/bfua.png\",\"Id\":2043137,\"OptimisedForMobile\":true,\"BookingNumber\":\"0640888123\",\"Name\":\"Bravoavia\"},{\"Status\":\"UpdatesComplete\",\"Type\":\"TravelAgent\",\"ImageUrl\":\"http:\\/\\/s1.apideeplink.com\\/images\\/websites\\/aebl.png\",\"Id\":1929340,\"OptimisedForMobile\":true,\"Name\":\"AeroBilet\"},{\"Status\":\"UpdatesComplete\",\"Type\":\"TravelAgent\",\"ImageUrl\":\"http:\\/\\/s1.apideeplink.com\\/images\\/websites\\/otua.png\",\"Id\":3513089,\"OptimisedForMobile\":false,\"Name\":\"OneTwoTrip\"},{\"Status\":\"UpdatesComplete\",\"Type\":\"TravelAgent\",\"ImageUrl\":\"http:\\/\\/s1.apideeplink.com\\/images\\/websites\\/tkua.png\",\"Id\":4045313,\"OptimisedForMobile\":true,\"Name\":\"Tickets.ua\"},{\"Status\":\"UpdatesComplete\",\"Type\":\"Airline\",\"ImageUrl\":\"http:\\/\\/s1.apideeplink.com\\/images\\/websites\\/fldu.png\",\"Id\":2498533,\"OptimisedForMobile\":true,\"BookingNumber\":\"0097142311000\",\"Name\":\"flydubai\"},{\"Status\":\"UpdatesComplete\",\"Type\":\"Airline\",\"ImageUrl\":\"http:\\/\\/s1.apideeplink.com\\/images\\/websites\\/ukra.png\",\"Id\":4155761,\"OptimisedForMobile\":false,\"BookingNumber\":\"+380445815050\",\"Name\":\"Ukraine International\"}],\"Segments\":[{\"Directionality\":\"Outbound\",\"OriginStation\":12911,\"DepartureDateTime\":\"2016-12-22T09:25:00\",\"ArrivalDateTime\":\"2016-12-22T10:25:00\",\"JourneyMode\":\"Flight\",\"DestinationStation\":12288,\"OperatingCarrier\":1571,\"FlightNumber\":\"25\",\"Duration\":60,\"Id\":0,\"Carrier\":1571},{\"Directionality\":\"Outbound\",\"OriginStation\":12911,\"DepartureDateTime\":\"2016-12-22T19:40:00\",\"ArrivalDateTime\":\"2016-12-22T20:40:00\",\"JourneyMode\":\"Flight\",\"DestinationStation\":12288,\"OperatingCarrier\":1571,\"FlightNumber\":\"23\",\"Duration\":60,\"Id\":1,\"Carrier\":1571},{\"Directionality\":\"Outbound\",\"OriginStation\":12428,\"DepartureDateTime\":\"2016-12-22T18:05:00\",\"ArrivalDateTime\":\"2016-12-22T20:00:00\",\"JourneyMode\":\"Flight\",\"DestinationStation\":14347,\"OperatingCarrier\":866,\"FlightNumber\":\"830\",\"Duration\":55,\"Id\":2,\"Carrier\":866},{\"Directionality\":\"Outbound\",\"OriginStation\":14347,\"DepartureDateTime\":\"2016-12-22T23:00:00\",\"ArrivalDateTime\":\"2016-12-22T23:15:00\",\"JourneyMode\":\"Flight\",\"DestinationStation\":12288,\"OperatingCarrier\":866,\"FlightNumber\":\"835\",\"Duration\":75,\"Id\":3,\"Carrier\":866},{\"Directionality\":\"Outbound\",\"OriginStation\":12911,\"DepartureDateTime\":\"2016-12-22T19:05:00\",\"ArrivalDateTime\":\"2016-12-22T21:05:00\",\"JourneyMode\":\"Flight\",\"DestinationStation\":14347,\"OperatingCarrier\":866,\"FlightNumber\":\"846\",\"Duration\":60,\"Id\":4,\"Carrier\":866}],\"Currencies\":[{\"DecimalSeparator\":\",\",\"ThousandsSeparator\":\"\u0412\u00A0\",\"SymbolOnLeft\":false,\"SpaceBetweenAmountAndSymbol\":false,\"Symbol\":\"\u0420\u0456\u0421\u0402\u0420\u0405.\",\"DecimalDigits\":2,\"Code\":\"UAH\",\"RoundingCoefficient\":0},{\"DecimalSeparator\":\",\",\"ThousandsSeparator\":\"\u0412\u00A0\",\"SymbolOnLeft\":false,\"SpaceBetweenAmountAndSymbol\":true,\"Symbol\":\"\u0421\u0402.\",\"DecimalDigits\":2,\"Code\":\"BYR\",\"RoundingCoefficient\":0}],\"Places\":[{\"ParentId\":4110,\"Type\":\"Airport\",\"Id\":12911,\"Code\":\"KBP\",\"Name\":\"Kyiv Boryspil\"},{\"ParentId\":4089,\"Type\":\"Airport\",\"Id\":12288,\"Code\":\"HRK\",\"Name\":\"Kharkiv\"},{\"ParentId\":4110,\"Type\":\"Airport\",\"Id\":12428,\"Code\":\"IEV\",\"Name\":\"Kyiv Zhuliany\"},{\"ParentId\":5319,\"Type\":\"Airport\",\"Id\":14347,\"Code\":\"MSQ\",\"Name\":\"Minsk International 2\"},{\"ParentId\":243,\"Type\":\"City\",\"Id\":4110,\"Code\":\"IEV\",\"Name\":\"Kyiv\"},{\"ParentId\":243,\"Type\":\"City\",\"Id\":4089,\"Code\":\"HRK\",\"Name\":\"Kharkiv\"},{\"ParentId\":248,\"Type\":\"City\",\"Id\":5319,\"Code\":\"MSQ\",\"Name\":\"Minsk\"},{\"Type\":\"Country\",\"Id\":243,\"Code\":\"UA\",\"Name\":\"Ukraine\"},{\"Type\":\"Country\",\"Id\":248,\"Code\":\"BY\",\"Name\":\"Belarus\"}]}";
		
		RequestDataContainer J = new RequestDataContainer(json);
		
		//J.getFlightInfoFromLegs(Legs);
	}
	*/
}






