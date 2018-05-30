"use strict";

const expect = require("chai").expect;
const codecheck = require("codecheck");
const testcase = require("./basic_testcase.json");
const app = codecheck.consoleApp(process.env.APP_COMMAND);

describe("Flexible FizzBuzz Overwrite", () => {

  it("should output a non-empty string when given correct inputs", () => {
    return app.codecheck(testcase[0].input).then( result => {
      expect(result.code).to.equal(0, "codecheck CLI should exit with status code 0");
      expect(result.stdout[0]).to.be.ok;
    });
  });

  testcase.forEach((t) => {
    let expectedOutput = t.output.toString();
    it(`should output '${expectedOutput}' when recieving input '${t.input.join(" ")}`, () => {
      return app.codecheck(t.input).then( result => {
        expect(result.code).to.equal(0, "codecheck CLI should exit with status code 0");

        let actualOutput = result.stdout.join("");
        if (typeof expectedOutput === "number") {
          expect(parseInt(actualOutput)).not.to.be.NaN;
        }
        expect(actualOutput).to.equal(expectedOutput);
      });
    });
  });
});
