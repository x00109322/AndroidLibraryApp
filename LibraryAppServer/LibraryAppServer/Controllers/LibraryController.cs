using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;

namespace LibraryAppServer.Controllers
{
    [Route("api/library")]
    [ApiController]
    public class LibraryController : ControllerBase
    {
        // test - hello world
        [HttpGet("test")]
        public string RunTestApi()
        {
            return "Hello World";                   // 200 OK
        }
    }
}
