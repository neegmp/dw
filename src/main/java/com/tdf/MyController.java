package com.tdf;

import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class MyController {

    private DeviceDAO  deviceDAO;
    public MyController(DeviceDAO  deviceDAO) {	        this.deviceDAO = deviceDAO;	    }

    @GET
    @Path("/hello/{name}")
    public Device greet(@QueryParam(value = "name") String nm){
    return new Device("MOB20200002","S7","Samsung","BIDB997F","M001");
    }

    @GET
    @UnitOfWork
    @Path("/devices")
    public List<Device> showDevices(@QueryParam(value = "name") String nm){
        return deviceDAO.findAll();
    }

    @POST
    @UnitOfWork
    @Path("/addDevice")
    @Consumes(MediaType.APPLICATION_JSON)
    public String addDevice(Device device){
        return deviceDAO.create(device);
    }

    @PUT
    @UnitOfWork
    @Path("/editDevice")
    @Consumes(MediaType.APPLICATION_JSON)
    public String editDevice(Device device){
        return deviceDAO.edit(device);
    }

    @GET
    @UnitOfWork
    @Path("/getDeviceById")
    @Consumes(MediaType.APPLICATION_JSON)
    public Object getDeviceById(@QueryParam(value = "deviceId") String deviceId){
        Object d = (Device) deviceDAO.findDeviceById(deviceId);
        if(null==d){
            return "Cannot Find the value...!!";
        }
        return d;
    }

    @GET
    @UnitOfWork
    @Path("/getDeviceByName")
    @Consumes(MediaType.APPLICATION_JSON)
    public Object getDeviceByName(@QueryParam(value = "deviceName") String name){
        Object d = (List<Device>) deviceDAO.findDeviceByName(name);
        if(null==d){
            return "Cannot Find the device with name : " + name + "...!!";
        }
        return d;
    }

}
