package esa.response

import skinny.http.Response

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

/**
  * EsaResponse は REST したことによって生成される Skinny フレームワークの {@link Response} を受け取り、
  * そのオブジェクトから status, headers, body を個別に分割して保持する値オブジェクトです。
  *
  * Esa API アプリケーション内で REST した結果を扱いたい際には必ずこのオブジェクトを通る必要があります。
  *
  * @param _skinnyResponse Skinny フレームワークの Response
  */
case class EsaResponse(_skinnyResponse: Response) {

  def body: String = _skinnyResponse.textBody

  def headers: Map[String, String] = _skinnyResponse.headers

  def header(name: String): Option[String] = _skinnyResponse.header(name)

  def status: Int = _skinnyResponse.status
}
