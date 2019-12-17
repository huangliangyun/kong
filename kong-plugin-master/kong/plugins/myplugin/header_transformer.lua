local kong = kong
local type = type
local find = string.find
local lower = string.lower
local match = string.match
local noop = function() end

local _M = {}

local function iter(config_array)
  if type(config_array) ~= "table" then
    return noop
  end

  return function(config_array, i)
    i = i + 1

    local header_to_test = config_array[i]
    if header_to_test == nil then -- n + 1
      return nil
    end

    local header_to_test_name, header_to_test_value = match(header_to_test, "^([^:]+):*(.-)$")
    if header_to_test_value == "" then
      header_to_test_value = nil
    end

    return i, header_to_test_name, header_to_test_value
  end, config_array, 0
end

local function is_json_body(content_type)
  return content_type and find(lower(content_type), "application/json", nil, true)
end

local function is_body_transform_set(conf)
  return #conf.add.json     > 0 or
         #conf.remove.json  > 0
end

-- export utility functions
_M.is_json_body = is_json_body
_M.is_body_transform_set = is_body_transform_set


function _M.transform_headers(conf, headers)
  -- remove headers
  for _, header_name in iter(conf.remove.headers) do
      kong.response.clear_header(header_name)
  end


  -- add headers
  for _, header_name, header_value in iter(conf.add.headers) do
    if headers[header_name] == nil and header_value then
      kong.response.set_header(header_name, header_value)
    end
  end

  -- Removing the content-length header because the body is going to change
  if is_body_transform_set(conf) and is_json_body(headers["Content-Type"]) then
    kong.response.clear_header("Content-Length")
  end
end

return _M
