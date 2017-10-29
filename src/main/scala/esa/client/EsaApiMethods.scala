package esa.client

import esa.response.EsaResponse

/*
 * Copyright 2017 Yuki Toyoda
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

case class EsaApiMethods(private val esaClient: EsaClient) {

  private type PathStr = String

  private val currentTeam: String = esaClient.currentTeam

  def user(headers: Map[String, String] = Map(),
           params: Map[String, String] = Map())
    : EsaResponse =
    esaClient.sendGet("/v1/user", params, headers)

  def teams(headers: Map[String, String] = Map(),
            params: Map[String, String] = Map()): EsaResponse =
    esaClient.sendGet("/v1/teams", params, headers)

  def team(teamName: String,
           headers: Map[String, String] = Map(),
           params: Map[String, String] = Map()): EsaResponse =
    esaClient.sendGet(s"/v1/teams/$currentTeam", params, headers)

  def stats(headers: Map[String, String] = Map(),
            params: Map[String, String] = Map()): EsaResponse =
    esaClient.sendGet(s"/v1/teams/$currentTeam/stats", params, headers)

  def members(headers: Map[String, String] = Map(),
              params: Map[String, String] = Map()): EsaResponse =
    esaClient.sendGet(s"/v1/teams/$currentTeam/members", params, headers)

  def posts(headers: Map[String, String] = Map(),
            params: Map[String, String] = Map()): EsaResponse =
    esaClient.sendGet(s"/v1/teams/$currentTeam/posts", params, headers)

  def post(postNumber: Long,
           headers: Map[String, String] = Map(),
           params: Map[String, String] = Map(),
           requestBody: String): EsaResponse =
    esaClient.sendPost(s"/v1/teams/$currentTeam/posts/$postNumber",
                       params,
                       headers,
                       requestBody)

  def createPost(headers: Map[String, String] = Map(),
                 params: Map[String, String] = Map(),
                 requestBody: String): EsaResponse =
    esaClient.sendPost(s"/v1/teams/$currentTeam/posts",
                       params,
                       headers,
                       requestBody)

  def updatePost(postNumber: Long,
                 headers: Map[String, String] = Map(),
                 params: Map[String, String] = Map(),
                 requestBody: String): EsaResponse =
    esaClient.sendPatch(s"/v1/teams/$currentTeam/posts/$postNumber",
                        params,
                        headers,
                        requestBody)

  def deletePost(postNumber: Long,
                 headers: Map[String, String] = Map(),
                 params: Map[String, String] = Map()): EsaResponse =
    esaClient.sendDelete(s"/v1/teams/$currentTeam/posts/$postNumber",
                         params,
                         headers)
}
